/* 요청 정보 다루기
*/


const http = require('http')
const url = require('url')
const qs = require('querystring')

const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)
  if(urlInfo.pathname !='/post.do'){
    response.end()
    return
  }
  var buf = ''

    request.on('data', (data) => {
      buf += data
    })

    request.on('end', () => {
      response.writeHead(200, {
        'Content-Type' : 'text/plain;charset=UTF-8'
      })
      var params = qs.parse(buf)

      response.write('name=' + params.name + '\n')
      response.write('age=' + params.age + '\n')
      response.write('tel=' + params.tel + '\n')
      response.end();
    })
})
server.listen(8888)
console.log("실행중")
