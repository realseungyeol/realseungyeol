/* 요청 정보 다루기
조건문을 사용하여 URL 구분
*/

const http = require('http')
const url = require('url')
const qs = require('querystring')

const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)
  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })

  if(urlInfo.pathname =='/get.do'){
    response.write('get 처뤼 \n')
    response.write('name=' + urlInfo.query.name + '\n')
    response.write('age=' + urlInfo.query.age + '\n')
    response.write('tel=' + urlInfo.query.tel + '\n')
    response.end();

  } else if (urlInfo.pathname == '/post.do') {
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
      response.end();
    })
  } else if (urlInfo.pathname =='/hello.do'){
    response.write('안녕하세요')
    response.end()

  } else {
    response.write('해당URL을 지원하지 않아')
    response.end()
  }
})
server.listen(8888)
console.log("실행중")
