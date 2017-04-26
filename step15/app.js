
var express = require('express')
var bodyParser = require('body-parser')
var path = require('path')
var cons = require('consolidate')

var handlebars = require('handlebars')


var app = express()
app.use(express.static('public'))
app.use(bodyParser.urlencoded({extended: false}))

app.engine('html', cons.handlebars)
app.set('view engine', 'html')
app.set('views', path.join(__dirname, '/views'))

app.use('/student', require('./control/student-control'))

app.get('/', (request, response) => {
  response.render('index')
})

app.listen(8888, () => {
  console.log('서서비작')
})
