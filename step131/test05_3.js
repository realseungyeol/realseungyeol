/* 탬플릿 엔진 사용하기 - 외부에서 템플릿 소스를 가져오기
'fs'모듈을 이용한다
*/


const fs = require('fs')
const path = require('path')

var templatePath = path.join(__dirname, 'test05_3_template.txt')

fs.readFile(templatePath, 'utf-8', (err, data) => {
  if(err) throw error
  console.log(data)
})
