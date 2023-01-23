
const IN = [
    '[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]',
    '[[1]]',
];

let i =0;
for (let str of IN) {
    i++;
    console.log(convertToCArray(str, `in${i}`));
}

function convertToCArray(json, name = 'arr') {
    const c_arr = json.replaceAll('[', '{').replaceAll(']','}');

    const arr = JSON.parse(json);
    const conlumns = arr[0].length;

    return `int ${name}[][${conlumns}] = ${c_arr};`;
}