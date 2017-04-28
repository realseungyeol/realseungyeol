// 학생 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const studentDao = require('../dao/student-dao')
const memberDao = require('../dao/member-dao')
const studentService = require('../service/student-service')

const connection = datasource.getConnection()
studentDao.setConnection(connection)
memberDao.setConnection(connection)
studentService.setStudentDao(studentDao)
studentService.setMemberDao(memberDao)

const router = express.Router()

router.get('/list.do', (request, response) => {
  var pageNo = 1,
      pageSize = 3;
  if (request.query.pageNo) {
    pageNo = parseInt(request.query.pageNo)
  }
  if (request.query.pageSize) {
    pageSize = parseInt(request.query.pageSize)
  }

  studentService.list(pageNo, pageSize, (results, totalCount) => {
    var lastPageNo = parseInt(totalCount / pageSize) + (((totalCount % pageSize) > 0) ? 1 : 0)

    response.render('student/index', {
      data: results,
      pageNo: pageNo,
      nextPageNo: pageNo + 1,
      prevPageNo: pageNo - 1,
      disabledPrevBtn: (pageNo == 1) ? 'disabled' : '',
      disabledNextBtn: (pageNo == lastPageNo) ? 'disabled' : ''
    })
  }, (error) => {
    response.render('error', {'message': 'student list error11'})
    console.log(error)
  })
})

router.get('/detail.do', (request, response) => {
  var no = parseInt(request.query.no)
  studentService.detail(no, (result) => {
    response.render('student/view', {
      detail: true,
      data: result,
      checkedWorking: (result.work == 'Y' ? 'checked' : '')
    })
  }, (error) => {
      response.render('error', {'message': 'student list error22'})
    console.log(error)
  })
})

router.post('/update.do', (request, response) => {
  studentService.update({
    no: request.body.no,
    working: (request.body.working == undefined ? 'N' : 'Y'),
    schoolName: request.body.schoolName,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'student list error'})
    console.log(error)
  })
})

router.get('/delete.do', (request, response) => {
  var no = parseInt(request.query.no)
  studentService.delete(no, () => {
    response.redirect('list.do')
  },(error)=>{
    response.render('error', {'message': 'student list error'})
    console.log(error)
  })
})

router.get('/form.do', (request, response) => {
  response.render('student/view')
})

router.post('/add.do', (request, response) => {
  studentService.insert({
    working: (request.body.working == undefined ? 'N' : 'Y'),
    schoolName: request.body.schoolName,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'student list error'})
    console.log(error)
  })
})

module.exports = router
