/* 탬플릿 엔진 사용하기 - 배열 데이터 처리
템플릿 문법
{{#each 배열프로퍼티명}}

{{/each}}
*/


const handlebars = require('handlebars')

var str = '\
{{#each students}}\
{{no}}, {{name}}, {{email}}\n \
{{/each}}\
'
var data = {
  students: [
    {no: 1, name : '홍길동1', email: 'hone1#test.com'},
    {no: 2, name : '홍길동2', email: 'hone2#test.com'},
    {no: 3, name : '홍길동3', email: 'hone3#test.com'},
    {no: 4, name : '홍길동4', email: 'hone4#test.com'}
  ]
}

// 1)탬플릿 엔진을 이용하여 템플릿 소스를 변환 함수를 준비한다

var template = handlebars.compile(str)

// 탬플릿 엔진으로부터 리턴 받은 함수에게 데이터를 전달한다.
// 템플릿 소스에 데이터가 적용된 결과 문자열을 얻는다.
var result = template(data)



console.log(result)
