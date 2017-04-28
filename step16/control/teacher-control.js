// 학생 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const teacherDao = require('../dao/teacher-dao')
const memberDao = require('../dao/member-dao')
const teacherService = require('../service/teacher-service')

const connection = datasource.getConnection()
teacherDao.setConnection(connection)
memberDao.setConnection(connection)
teacherService.setMemberDao(memberDao)
teacherService.setTeacherDao(teacherDao)

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

  teacherService.list(pageNo, pageSize, (results, totalCount) => {
    var lastPageNo = parseInt(totalCount / pageSize) + (((totalCount % pageSize) > 0) ? 1 : 0)

    response.render('teacher/index', {
      data: results,
      pageNo: pageNo,
      nextPageNo: pageNo + 1,
      prevPageNo: pageNo - 1,
      disabledPrevBtn: (pageNo == 1) ? 'disabled' : '',
      disabledNextBtn: (pageNo == lastPageNo) ? 'disabled' : ''
    })
  }, (error) => {
    response.render('error', {'message': 'teacherlist error11'})
    console.log(error)
  })
})

router.get('/detail.do', (request, response) => {
  var no = parseInt(request.query.no)
  teacherService.detail(no, (result) => {
    response.render('teacher/view', {
      detail: true,
      data: result
    })
  }, (error) => {
      response.render('error', {'message': 'teacher list error22'})
    console.log(error)
  })
})

router.post('/update.do', (request, response) => {
  teacherService.update({
    no: request.body.no,
    homepage: request.body.homepage,
    facebook: request.body.facebook,
    twitter: request.body.twitter,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'teacher list error'})
    console.log(error)
  })
})

router.get('/delete.do', (request, response) => {
  var no = parseInt(request.query.no)
  teacherService.delete(no, () => {
    response.redirect('list.do')
  },(error)=>{
    response.render('error', {'message': 'teacher list error'})
    console.log(error)
  })
})

router.get('/form.do', (request, response) => {
  response.render('teacher/view')
})

router.post('/add.do', (request, response) => {
  teacherService.insert({
    homepage: request.body.homepage,
    facebook: request.body.facebook,
    twitter: request.body.twitter,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'teacher list error'})
    console.log(error)
  })
})

module.exports = router
