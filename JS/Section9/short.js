//Toán tử spread để mở rộng một mảng thành các phần riêng lẻ
const restaurent = {
    name: "Cassico Italiano",
    location: "Via Anglo Tavanti 23, Firenze, Italy",
    categories: ["Italian", "bruscheta", "Garlic Bread", "Caprese salad"],
    startMenu: ["focaccia", "Bruschetta", "Garlic Bread", "Capress Salad"],
    mainMenu: ["Pizza", "Pasta", "Risotto"],
  
    openingHours: {//thuộc tính trong đối tượng openingHours
      thu: {
        open: 12,
        close: 22,
      },
      fri: {
        open: 12,
        close: 22,
      },
      sat: {
        open: 11,
        close: 24,
      },
    },
  
    //tạo 1 hàm truyền 2 tham số, trả về một mảng chứa 2 phần tử đó
    order: function (starIndex, mainIndex) {
      //Phần tử đầu tiên là món ăn từ startMenu tại vị trí startIndex
      return [this.startMenu[starIndex], this.mainMenu[mainIndex]];
    },

    //Khi chúng ta nhận cấu trúc đó, chúng ta sẽ thực hiện hủy cấu trúc ngay lặp tức
    orderDelivery: function({
      starIndex = 1, mainIndex = 0, time = '20:00', address}){
        console.log(
          `Order received! ${this.startMenu[starIndex]} and ${this.mainMenu[mainIndex]} will be delived to ${address} at ${time} `
        );
      },

      orderPasta: function(img1, img2, img3){
        console.log(`Here is your declicious pasta with ${img1}, ${img2} and ${img3}`);
      },

      orderPizza: function(mainIngredient, ...otherInge){//3 CHẤM LÀ CÒN LẠI TẠO THÀNH MỘT MẢNG
          console.log(mainIngredient);
          console.log(otherInge);
      }

  };
 // || giá trị sai là '', 0, undefined
 // || nó trả về giá trị đúng
  console.log(3 || 'Jonas');//tra ve gía trị đầu tiên
  console.log(true || 0);//true
  console.log('' || 0 || 'Jones')//Jones
  console.log(undefined || null);//null
 // && giá trị sai là 0, null
const guests = restaurent.Guests ? restaurent.Guests : 10;
console.log(guests);

// ---AND---Tra ve giá trị sai/Duyệt hết lấy giá trị sai/ đúng hết thì nó lấy cuối
console.log(0 && 'Jonas');//0
console.log(7 && 'Jonas' && null );//null

if(restaurent.orderPizza){
  restaurent.orderPizza('mushorm', 'spinach')
}
//Thay thế if bằng && Nếu đúng thì thực hiện cái tiếp theo
restaurent.orderPizza && restaurent 

restaurent.HIHI = 0;
console.log(restaurent.HIHI || 10);//10
//Nullish: null and undefined (Not 0 or '')
console.log(restaurent.HIHI ?? 10)//Nếu restaurent.HIHI là null and undefined thì nó trả về mặc đinh là 10, Ngược lại thì nó trả về cái điều kiện đang là if
//10

// -------------
console.log('---------||--------&&-------??---------')
const rest1 = {
  name: 'Vương Phạm',
  owner: 'Ngoc anh trang',
  ages: 0
}
const rest2 = {
  name: 'Huyền Lan Anh',
  owner: 'Trang Pháp',
}
rest1.owner = rest1.owner || 10;
rest2.hihi = rest2.hihi || 20;

rest1.ages ||= 10;//10 Nếu đúng thì nó không gán, nếu sai nó mới gán
rest1.ages ??= 20;//10 Kiểm tra null, undefined
rest1.ages &&= 32;//Đúng hết thì gán cho cái cuối
console.log(rest1);
console.log(rest2);

console.log('-------for of-------');
// const menu = [...restaurent.startMenu, ...restaurent.mainMenu];

// for(const item of menu) console.log(item);

// for (const [i, j] of menu.entries()){
//   console.log(`${i + 1}: ${j}`);
// }
// console.log('------?.-------');
// console.log(restaurent.openingHours.mon?.open);
// console.log(restaurent.openingHours.thu?.open);
// const days = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
// for(const day of days){
//     // const open = restaurent.openingHours[day]?.open || 'closed';
//     const open = restaurent.openingHours[day]?.open ?? 'closed';
//     console.log(`On ${day}, we open at ${open}`);
// }
// //Methods
// console.log(restaurent.order?.(0, 1) ?? 'Method does not exits');//thứ 2
// console.log(restaurent.orderDelivery?.(0, 1));//undefined
// //Arrays
// const users = [{name: 'Jones', email: 'helllogamsd'}];
// console.log(users[0]?.name ?? 'User array empty');//Jones

// console.log('------Vòng lặp đối tượng theo gián tiếp------');
// for(const day of Object.keys(openingHours)){
//   console.log(day);//thu, fri, sat/Lặp qua tên của các đối tượng trong đối tượng openingHours
// }
console.log('-------Set--------');
const ordersSet = new Set([
  'Pasi',
  'Kim Mi Jung Ki',
  'Kim Mi Jung Ki',
  'Hâna Kim Sang',
  'Com Po No Khum',
  'Com Po No Khum'
]);
console.log(ordersSet);//Lọc số phần tử trong mảng
console.log(new Set('Jonas'));//Tách số phần tử trong chuỗi
console.log(ordersSet.size);//3
console.log(ordersSet.has('Pasi'));//Tồn tại thì nó hiện true, ngược lại là false
ordersSet.add('HOHO');//Thêm phần tử vào một tập hợp
ordersSet.delete('Com Po No Khum');//xóa đi phần tử đó
// ordersSet.clear();Xóa hết
//Set là để biết được phần tử đó có nằm trong tập hợp không và không thể lấy nó ra được
console.log(ordersSet);
for(const order of ordersSet) console.log(order);//lấy ra các phần tử không trùng nhau trong tập hợp


const staff = ['water', 'Chef', 'Waiter', 'Manger', 'Chef'];
console.log(new Set(staff));//Duyệt các phần tử không trùng nhau
//Chuyển đổi một tập hợp thành mảng
console.log([...new Set(staff)]);//Chuyển đổi một tập hợp thành mảng
console.log(new Set(['water', 'Chef', 'Waiter', 'Manger', 'Chef']).size);//4
console.log(new Set('hihdfgdsfghsdhi').size);//6 Điếm được bao nhiêu chữ khác nhau trong chuỗi

console.log('---Map-----');

const rest = new Map();
rest.set('name', 'Classicololo');
rest.set(1, 'Một nè');
rest.set(2, 'haha');
console.log(rest.set('hoho', 'cạp cạp'));

rest.set('catakijumi', 'dong 1')
.set('open', 11)
.set('close', 12)
.set(true, 'We are open :D')
.set(false, 'We are close :(');

console.log(rest.get('name'));//Lấy key hiên giá trị
console.log(rest.get(true));

const time = 21;
//Boolean làm chìa khóa bản đồ
rest.get( time > rest.get('open') && time < rest.get('close'));
//Sử dụng has giống như set
rest.delete(2);//Xóa phần tử thứ mấy
//Có thể dùng size()
console.log('-----Mảng Map()-------')
const question = new Map([
  ['1', 2],
  [1, 9],
  ['3 ne', 'ba'],
  ['4 ne', 'bốn'],
  ['5 ne', 'năm']
]);
//convert object to map
// console.log(Object.entries(openingHours));
// const hoursMap = new Map(Object.entries(openingHours));
// console.log(hoursMap);

// =======================

//Ví dụ typeof trả về kiểu dữ liệu 
//typeof 42; // "number"
//typeof "hello"; // "string"
//typeof true; // "boolean"
//typeof undefined; // "undefined"
//typeof null; // "object" (lưu ý: null được coi là một kiểu dữ liệu lỗi khi sử dụng typeof)
//typeof {}; // "object"
//typeof []; // "object" (lưu ý: mảng cũng được coi là một đối tượng khi sử dụng typeof)
//typeof function(){}; // "function"

for(const [key, value] of question){
  //Ví dụ typeof trả về kiểu dữ liệu 
  if(typeof key === 'number') console.log(`Answer ${key} : ${value}`);//1
}
// const answer = Number(prompt('Your answer'));
// console.log(answer);
// console.log(question.get(question.get('1') === answer));

//convert map to Arrays
// console.log([...question.values()]);
console.log('--------Sử dụng mảng khi cần ánh xạ// Sử dụng object khi bạn dùng function mà cần this đến vị trí mình cần dùng----------');
console.log('------String----');

const airline = 'Tap air po';
const plane = 'A3434';

console.log(plane[0]);//A
console.log(plane[1]);//3
console.log(plane[2]);//4

console.log('B342'[0]);//B

console.log(airline.length);//10
console.log('B737'.length);//4

console.log(airline.indexOf('a'));//vị trí thứ tự từ trên xuống dưới trong chuỗi
console.log(airline.lastIndexOf('a'));//vị trí thứ tự từ cuối lên trên trong chuỗi

//indexOf Nếu không tồn tại thì trả về -1

console.log(airline.slice(4));//vị trí quá trình trích xuất sẽ bắt đầu
console.log(airline.slice(4, 7));//vị trí quá trình trích xuất sẽ bắt đầu và vị trí kết thúc

console.log(airline.slice(0, airline.indexOf(' ')));//Tap
//tương tự với lastIndextOf()

console.log(airline.slice(-2));// po lấy ngược từ cuối lên
console.log(airline.slice(1, -1));
console.log(airline.slice(-2));
// ---
const checkMiddleSeat = function (seat){
  const s = seat.slice(-1);
  if(s === 'B' || s === 'E') console.log('You got the middle seat');
  else console.log('you got lucky');
};

checkMiddleSeat('11B');//if
checkMiddleSeat('23C');//else
checkMiddleSeat('3E');//if

// ---
//lấy chuỗi đặt vào 1 đối tượng
console.log(new String('Jona'));
console.log(typeof new String('Jona'));//Object
console.log( typeof new String('Jona').slice(1));//String

console.log(airline.toLowerCase());
console.log(airline.toUpperCase());

const passHIhi = 'joHDWn';
const passengerCorrect = passHIhi[0].toUpperCase();
console.log(passengerCorrect);

//Check email
const email = 'hello@join.io';
const loginEmail = '  Hello2Jonas.Io \n';

const lowerEmail = loginEmail.toLowerCase().trim();//thành chữ thường
//trim() bỏ hết khoản trống

console.log(lowerEmail);
console.log(email === lowerEmail)//true
//replacing
const priceGB = '289,97';
const priceUS = priceGB.replace(',', '.');//thay thế
console.log(priceUS);

//Booleans
const planes = 'Ahfs34J';
console.log(planes.includes('Ahfs'));//true kiểm tra xem nó có nằm đâu trong chuỗi không
console.log(planes.startsWith('Ah'));//true kiểm tra đầu chuỗi
console.log(planes.endsWith('4J'));//true kiểm tra cuối chuỗi

//Practise exercise
const checkBaggage = function(item){
  const baggage = item.toLowerCase();
  if(baggage.includes('knife') || baggage.includes('gun')){
    console.log('You are not allowed on broard');
  }else{
    console.log('Welcome aboard!');
  }
}

checkBaggage('I have a laptop, some Food and a pocket Knife');

console.log('--------splice() AND join()--------------');

//Tách chuỗi thành mảng
console.log('a+very+nice+string'.split('+'));
console.log('Jonas Schmedtman'.split(' '));

const [fistName, lastName] = 'Jonas schmedtmann'.split(' ');//tach chuỗi rồi gán vào biến

const newName = ['Mr.', fistName, lastName].join(' ');//ngược lại của tách chuỗi split()

console.log(newName);

const passenger = 'Jessica ann smith divis';

const capializeName = function(name){
  const names = name.split(' ');
  const nameUpper = [];
  for(const n of names){
    // nameUpper.push(n[0].toUpperCase() + n.slice(1));//chữ cái đầu viết hoa và lấy chuỗi còn lại
    nameUpper.push(n.replace(n[0], n[0].toUpperCase()));//thay thế ký tự đầu tiên

  }
  console.log(nameUpper.join(' '));
};
capializeName('hihi ababa ninini');//Hihi Ababa Ninini

//padding
console.log('-------Đệm chuỗi------');
const message = 'Go to gate 23';
//trèn dấu + và đủ số chuỗi
console.log(message.padStart(20, '+').padEnd(30, '+'));//++++++++++++Go to gate 23+++++
console.log('hihi'.padStart(20, '+')); //+++++++++++++++++++++hihi

const mask = function(number){
  const str = number + ' ';
  const last = str.slice(-4);
  console.log('padStart(số text, "text muốn thêm" )');
  return last.padStart(str.length, '*');
}
console.log(mask(89765432456789));

console.log('------Chuỗi.repeat(LẶP)------');
const messanger2 = 'Bad waether ...All Departues Departues';
console.log(messanger2.repeat(5));

const planeInLine = function(n){
  console.log(`There are ${n} planes in line ${'Yến nè'.repeat(n)}`);
};
planeInLine(3);//Nhân đôi 3 lần chuỗi Yến nè

console.log('-----------');
const flights =
  '_Delayed_Departure;fao93766109;txl2133758440;11:25+_Arrival;bru0943384722;fao93766109;11:45+_Delayed_Arrival;hel7439299980;fao93766109;12:05+_Departure;fao93766109;lis2323639855;12:30';
// split() là tách chuỗi thành mảng
  console.log(flights.split('+'));
for(const flight of flights.split('+')){
  const [type, from, to, time] = flight.split(';');
  const output = ` ${type.startsWith('_Delayed') ? 'đỏ' : ''} ${type.replaceAll('_', ' ')} ${from.toUpperCase().slice(0, 3)} ${to.toUpperCase().slice(0, 3)}( ${time.replace(":", "h")})`.padStart(35);//padStart căn chỉnh text cho ok hơn
  console.log(output);
}