const express = require('express')
const datasource = require('../util/datasource')
const studentDao = require('../dao/student-dao')
const studentService = require('../service/student-service')

const router = express.Router()

router.get('/main.do', (request, response) => {
  response.remder('student/index')
})

module.exports = router
