/* 요청 정보 다루기
조건문을 사용하여 URL 구분
*/

const http = require('http')
const url = require('url')
const qs = require('querystring')

function get(request, response) {
  response.write('get 처뤼 \n')
  response.write('name=' + request.query.name + '\n')
  response.write('age=' + request.query.age + '\n')
  response.write('tel=' + request.query.tel + '\n')
  response.end()
}

function post(request, response) {
  var buf = ''
  request.on('data', (data) => {
    buf += data
  })
  request.on('end', ()=> {
    var params = qs.parse(buf)
    response.write('post 처뤼 \n')
    response.write('name=' + params.name + '\n')
    response.write('age=' + params.age + '\n')
    response.write('tel=' + params.tel + '\n')
    response.end()
  })
}

function hello(request, response) {
  response.write('안녕하세요')
  response.end()
}
function notfound(request, response) {
response.write('해당URL을 지원하지 않아')
response.end()
}
const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)
  if(request.method == 'GET') {
    request.query = urlInfo.query
  }
  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })

  if(urlInfo.pathname =='/get.do'){
    get(request, response)
  } else if (urlInfo.pathname == '/post.do') {
    post(request, response)
  } else if (urlInfo.pathname =='/hello.do'){
    hello(request, response)
  } else {
    notfound(request, response)
  }
})
server.listen(8888)
console.log("실행중")
