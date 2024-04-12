// Map
const currencies = new Map([
    ['USD', 'United States dollar'],
    ['EUR', 'Euro'],
    ['GBP', 'Pound sterling'],
  ]);
  
  currencies.forEach(function (value, key, map) {
    console.log(`${key}: ${value}`);
  });
 
let arr = ['a', 'b', 'c', 'd', 'e'];

console.log(arr.slice(2));//trích xuất chuỗi nó ra chuỗi, mảng ra mảng
console.log([...arr]);

//splice: các phương thức giống slice chỉ khác là nó xóa chứ không phải tách
console.log(arr.splice(2));//Lấy phần từ thứ mấy đến hết rồi xóa
console.log(arr.reverse());//Đảo ngược vị trí

//concat() nối mảng, nối chuỗi
const arr1 = ['hihi', 'haha'];
const letter = arr.concat(arr1);
console.log(letter);//nôi mang
console.log([...arr, ...arr1]);

//Join()
console.log(letter.join('-'));// b-a-hihi-haha
console.log('--------------');
console.log(arr[0]);//b
console.log(arr.at(0));//b
console.log(arr[arr.length - 1]);//a //khi nào muốn lấy vị trí thì độ dài mảng trừ đi một(là trừ cái mảng cần lấy)
console.log(arr.slice(-1));//lấy ngược từ cuối lên
console.log(arr.at(-1));//lấy ngược từ cuối lên
//at() có thể áp dụng cả chuỗi và mảng
console.log('--------forOf----------');
const movements = [200, 450, -400, 3000, -650, -130, 70, 1300];
for(const movement of movements){
  if(movement > 0) console.log(`You deposited ${movement}`);
  else console.log(`You withdrew ${Math.abs(movement)}`);
};
console.log('--------entries() giá trị và key----------');
 for(const [i, movement] of movements.entries()){
  if(movement > 0) console.log(`Movement ${i + 1}: you deposited ${movement}`);
  else console.log(`Movement ${i + 1}: you withdrew ${Math.abs(movement)}`);
};
console.log('--------forEach----------');
movements.forEach(function(movement){
  if(movement > 0) console.log(`You deposited ${movement}`);
  else console.log(`You withdrew ${Math.abs(movement)}`);
});
console.log('-----for each with maps and sets-----------');
const curren = new Map([
  ['USD', 'United States dollar'],//key, value
  ['EUR', 'Euro'],
  ['GBP', 'Pound sterling']
]);
curren.forEach(function(value, key, map){
    console.log(`${key} : ${value}`);
});
//Set
const currenUnique = new Set([
  'USD', 'GBP', 'USD', 'EUR', 'EUR'
]);
console.log(currenUnique);
currenUnique.forEach(function(value, key, map){
  console.log(`${key} : ${value}`);
});
