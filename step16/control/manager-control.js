// 학생 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const managerDao = require('../dao/manager-dao')
const memberDao = require('../dao/member-dao')
const managerService = require('../service/manager-service')

const connection = datasource.getConnection()
managerDao.setConnection(connection)
memberDao.setConnection(connection)
managerService.setManagerDao(managerDao)
managerService.setMemberDao(memberDao)

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

  managerService.list(pageNo, pageSize, (results, totalCount) => {
    var lastPageNo = parseInt(totalCount / pageSize) + (((totalCount % pageSize) > 0) ? 1 : 0)

    response.render('manager/index', {
      data: results,
      pageNo: pageNo,
      nextPageNo: pageNo + 1,
      prevPageNo: pageNo - 1,
      disabledPrevBtn: (pageNo == 1) ? 'disabled' : '',
      disabledNextBtn: (pageNo == lastPageNo) ? 'disabled' : ''
    })
  }, (error) => {
    response.render('error', {'message': 'manager list error11'})
    console.log(error)
  })
})

router.get('/detail.do', (request, response) => {
  var no = parseInt(request.query.no)
  managerService.detail(no, (result) => {
    response.render('manager/view', {
      detail: true,
      data: result
    })
  }, (error) => {
      response.render('error', {'message': 'manager list error22'})
    console.log(error)
  })
})

router.post('/update.do', (request, response) => {
  managerService.update({
    no: request.body.no,
    posi: request.body.posi,
    fax: request.body.fax,
    path: request.body.path,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'manager list error3'})
    console.log(error)
  })
})

router.get('/delete.do', (request, response) => {
  var no = parseInt(request.query.no)
  managerService.delete(no, () => {
    response.redirect('list.do')
  },(error)=>{
    response.render('error', {'message': 'manager list error4'})
    console.log(error)
  })
})

router.get('/form.do', (request, response) => {
  response.render('manager/view')
})

router.post('/add.do', (request, response) => {
  managerService.insert({
    posi: request.body.posi,
    fax: request.body.fax,
    path: request.body.path,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'

  }, (results) => {
    response.redirect('list.do')
  }, (error) => {
    response.render('error', {'message': 'manager list error5'})
    console.log(error)
  })
})

module.exports = router
