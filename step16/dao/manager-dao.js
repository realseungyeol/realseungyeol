"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  selectList(pageNo, pageSize, successFn, errorFn) {
    this.connection.query(
      'select m.mno, m.name, m.tel, m.email, mr.posi, mr.fax, mr.path \
      from mgr mr inner join memb m on mr.mrno=m.mno  \
      order by m.name asc \
      limit ?, ?',
      [(pageNo - 1) * pageSize, pageSize],
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) // connection.query()
  },//selectList()

  countAll(successFn, errorFn) {
    this.connection.query(
      'select count(*) cnt from mgr',
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) //connection.query()
  },//countAll()

  selectOne(no, successFn, errorFn) {
    this.connection.query(
      'select m.mno, m.name, m.tel, m.email, mr.posi, mr.fax, mr.path \
      from mgr mr inner join memb m on mr.mrno=m.mno \
      where mr.mrno=?',
      [no],
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results[0])
        }
      }) // connection.query()
  },//selectOne()

  insert(manager, successFn, errorFn) {
    this.connection.query(
      'insert into mgr(mrno,posi,fax,path) values(?,?,?,?)',
      [ manager.no, manager.posi, manager.fax ,manager.path],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //insert

  update(manager, successFn, errorFn) {
    this.connection.query(
      'update mgr set posi=?, fax=?, path=? where mrno=?',
      [manager.posi, manager.fax, manager.path, manager.no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //update()

  delete(no, successFn, errorFn) {
    this.connection.query(
      'delete from mgr where mrno=?',
      [no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      })
  }//delete()

}//exports
