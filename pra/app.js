
var miniexpress = require('./mini-express/mini-express')

var app = miniexpress()
app.use('static_dir', './static')

app.get('/get_test.do', (request, response) => {
  response.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'})
  response.write('GET 요청 처리!')
  response.write('name=' + request.query.name)
  response.write('age=' + request.query.age)
  response.write('tel=' + request.query.tel)
  response.end()
})

app.post('/post_test.do', (request, response) => {
  response.writeHead(200, {'Content-Type': 'text/plain;charset=UTF-8'})
  response.write('POST 요청 처리!')
  response.write('name=' + request.query.name)
  response.write('age=' + request.query.age)
  response.write('tel=' + request.query.tel)
  response.end()
})

app.listen(8888, function() {
  console.log('서서비작')
})
