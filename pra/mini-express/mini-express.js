var http = require('http')
var url = require('url')
var path = require('path')
var fs = require('fs')
var qs = require('querystring')

var envVars = {}
var getHandler = {}
var postHandler = {}

function notFound(request, response) {
  response.writeHead(200, {
    'Content-Type' : 'text/html;charset=UTF-8'
  })

  fs.readFile(path.join(__dirname, './html/http-404.html'), function(err, data) {
    if (err) throw err
    response.end(data)
  })
}

function isStaticResource(method, servicePath) {
  if (method != 'GET') // 정적 자원은 GET 요청에 대해서만 처리한다.
    return false

// 환경변수에서 'static_dir'이라는 이름으로 저장된 디렉토리 경로가 있다면
// 그 경로에서 클라이언트가 요규ㅜ하는 파일이 있는지 찾아봄
// 있으면true 없다면 false를 리턴
  if (envVars.static_dir) {
    return fs.existsSync(path.join(__dirname, '..', envVars.static_dir, servicePath))
  }
  return false
}

function processStaticResource(servicePath, response) {
  var filePath = path.join(__dirname, '..', envVars.static_dir, servicePath)
  fs.readFile(filePath, (err, data) => {
    if (err) {
      response.write('URL 자원 처리중 오류 발생!')
      response.end()
      return
    }
    response.end(data)
  })
}

function findHandler(method, servicePath) {
  if (method == 'GET') {
    return getHandler[servicePath]
  } else if (method == 'POST') {
    return postHandler[servicePath]
  }
  return null
}

var server = http.createServer((request, response) => {
  var urlInfo = url.parse(request.url, true)

  if (isStaticResource(request.method, urlInfo.pathname)) {
    processStaticResource(urlInfo.pathname, response)
    return;
  }


    var handler = findHandler(request.method, urlInfo.pathname);

    if (handler) {
      if (request.method == 'GET') {
        request.query = urlInfo.query
        handler(request, response)

      } else if (request.method == 'POST') {
        var queryString = ''
        request.on('data', function(data) {
          queryString += data
        })
        request.on('end', function() {
          request.query = qs.parse(queryString)
          handler(request, response)
        })
      }
    } else {
      notFound(request, response)
    }
  })
module.exports = () => {
  return {
    use(name, value) {
      envVars[name] = value
    },
    get(url, handler) {
      getHandler[url] = handler
    },
    post(url, handler) {
      postHandler[url] = handler
    },
    listen(port, cb) {
      server.listen(port, cb)
      /*port 번호로 서버를 시작한다
      서버가 가동되면 listening 이벤트가 발생하고
      지정된 함수 'cb'가 없다면, 호출되지 않는다
      즉서버가 시작된 후 알림을 받고 싶다면,
      두번째 파라미터에 함수를 전달하라*/
    }
  }
}
