"use strict"

module.exports = {
  setManagerDao(dao) {
    this.managerDao = dao
  },

  setMemberDao(dao) {
    this.memberDao = dao
  },

  list(pageNo, pageSize, success, error) {
    var obj = this
    this.managerDao.selectList(pageNo,  pageSize, function(manager) {
      obj.managerDao.countAll(function(result) {
        success(manager, result[0].cnt)
      }, error)
    }, error)
  },//list()

  detail(no, success, error) {
    this.managerDao.selectOne(no, success, error)
  },//detail()

  insert(manager, success, error) {
    var obj = this
    this.memberDao.insert(manager, function(result) {
      manager.no = result.insertId
      obj.managerDao.insert(manager, success, error)
    }, error)
  },//insert()

  update(manager, success, error) {
    var obj = this
    this.memberDao.update(manager, function(result) {
      obj.managerDao.update(manager, success, error)
    }, error)
  }, // update()

  delete(no, success, error) {
    var obj = this
    this.managerDao.delete(no, function(result) {
      obj.memberDao.delete(no, success, error)
    }, error)
  } // delete()
} // exports
