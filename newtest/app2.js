var gg = 0;
function shuffleRandom(){
        var ar = new Array();
        var temp;
        var rnum;

        for(var i=1; i<=9; i++){
            ar.push(i);
        }

        for(var i=0; i< 9 ; i++)
        {
            rnum = Math.floor(Math.random() *9);
            temp = ar[i];
            ar[i] = ar[rnum];
            ar[rnum] = temp;
        }
        gg = ar
        return
}

shuffleRandom();
console.log(gg[0])
