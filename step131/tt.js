/*http 서버 만들기 Single 스레드 방식
한번에 한 클라이언트 요쳐ㅓㅇ만 처리
즉 한 클아이언트의 요청 처리가 끝나야만 다음 클라이언트 요청을 처리한다
=> 수 많은 사용자의 동시 요청을 처리하는 용도가 아니다
=> 특정 한개의 서비스를 순차적 빠르게 처리할 필요가 있을때 사용한다.
*/
const http = require('http')
const url = require('url')
const server = http.createServer((request, response) => {
  if (request.url == '/favicon.ico') {
    response.end();
    return;
  }
  console.log('클라이언트 요청이 들어옴')

  console.log('url=', request.url)

  var urlInfo = url.parse(request.url)

  console.log('href=', urlInfo.href)
  console.log('pathname=', urlInfo.pathname)
  console.log('search=', urlInfo.search)
  console.log('query=', urlInfo.query)


  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })

  // response.write('<html>\
  // <head>\
  //   <title>테스트</title>\
  // </head>\
  // <body>\
  //   <h1>안녕하세요!</h1>\
  // </body>\
  // </html>')
  response.write('안녕하세요ㅕ')
  response.end();
})


server.listen(7777)
console.log("서버싱핼중")
