// 학생 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const lectureDao = require('../dao/lecture-dao')
const teacherDao = require('../dao/teacher-dao')
const managerDao = require('../dao/manager-dao')
const classroomDao = require('../dao/classroom-dao')

const lectureService = require('../service/lecture-service')
const teacherService = require('../service/teacher-service')
const managerService = require('../service/manager-service')
const classroomService = require('../service/classroom-service')

const connection = datasource.getConnection()
lectureDao.setConnection(connection)
lectureService.setLectureDao(lectureDao)
managerDao.setConnection(connection)
managerService.setLectureDao(managerDao)
classroomDao.setConnection(connection)
classroomService.setLectureDao(classroomDao)

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

  lectureService.list(pageNo, pageSize, (results, totalCount) => {
    var lastPageNo = parseInt(totalCount / pageSize) + (((totalCount % pageSize) > 0) ? 1 : 0)

    response.render('lecture/index', {
      data: results,
      pageNo: pageNo,
      nextPageNo: pageNo + 1,
      prevPageNo: pageNo - 1,
      disabledPrevBtn: (pageNo == 1) ? 'disabled' : '',
      disabledNextBtn: (pageNo == lastPageNo) ? 'disabled' : ''
    })
  }, (error) => {
    response.render('error', {'message': 'lecture list error11'})
    console.log(error)
  })
})

router.get('/detail.do', (request, response) => {
  var no = parseInt(request.query.no)
  lectureService.detail(no, (result) => {
    response.render('lecture/view', {
      detail: true,
      data: result
    })
  }, (error) => {
      response.render('error', {'message': 'lecture list error22'})
    console.log(error)
  })
})

router.post('/update.do', (request, response) => {
  lectureService.update({
    title: request.body.titl,
    content: request.body.dscp,
    startDate: request.body.sdt,
    endDate: request.body.edt,
    quantity: request.body.qty,
    price: request.body.pric,
    hours: request.body.thrs,
    classroom: request.body.crmno,
    manager: request.body.mrno,
    no: request.body.lno

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'lecture list error3'})
    console.log(error)
  })
})

router.get('/delete.do', (request, response) => {
  var no = parseInt(request.query.no)
  lectureService.delete(no, () => {
    response.redirect('list.do')
  },(error)=>{
    response.render('error', {'message': 'lecture list error4'})
    console.log(error)
  })
})

router.get('/form.do', (request, response) => {
  classroomService.listName((classrooms) => {
    managerService.listName((managers) => {
      response.render('lecture/view', {
        classrooms: classrooms,
        managers: managers
      })
      (error)=> {
        respone.render('error', {
          "message" : 강의실 데이터를 가져오는중 오류

        })
    })
  } , (error)=> {
    respone.render('error', {
      "message" : 강의실 데이터를 가져오는중 오류
    })
  })
  response.render('lecture/view')
})

router.post('/add.do', (request, response) => {
  lectureService.insert({
    lno: request.body.lno,
    title: request.body.titl,
    content: request.body.dscp,
    startDate: request.body.sdt2,

    endDate: request.body.edt2,
    quantity: request.body.qty,
    price: request.body.pric,

    hours: request.body.thrs,
    classroom: request.body.crmno,
    manager: request.body.mrno

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'lecture list error5'})
    console.log(error)
  })
})

module.exports = router
