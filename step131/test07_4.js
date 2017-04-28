/* 요청 정보 다루기

*/

const http = require('http')
const url = require('url')
const qs = require('querystring')

var handlerMapping = {}

handlerMapping['/get.do'] = (request, response) => {
  response.write('get 처뤼 \n')
  response.write('name=' + request.query.name + '\n')
  response.write('age=' + request.query.age + '\n')
  response.write('tel=' + request.query.tel + '\n')
  response.end()
}

handlerMapping['/post.do'] = (request, response) => {
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

handlerMapping['/hello.do'] = (request, response) => {
  response.write('안녕하세요')
  response.end()
}
function notFound(request, response) {
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

  var fn = handlerMapping[urlInfo.pathname]
  if (fn != undefined) {
    fn(request, response)
  } else {
    notFound(request, response)
  }
})
server.listen(8888)
console.log("실행중")
