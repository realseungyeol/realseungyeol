/* 요청 정보 다루기
*/

const http = require('http')
const url = require('url')

const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)
  if(urlInfo.pathname !='/get.do'){
    response.end()
    return
  }

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })
  response.write('name=' + urlInfo.query.name + '\n')
  response.write('age=' + urlInfo.query.age + '\n')
  response.write('tel=' + urlInfo.query.tel + '\n')
  response.end();
})
server.listen(8888)
console.log("실행중")
