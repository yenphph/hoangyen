const bookings = [];
const createBooking = function (flightNum,
    numPassengers = 1,
    price = 199 * numPassengers) {
    const booking = {
        flightNum,
        numPassengers,
        price
    }
    console.log(booking);
    bookings.push(booking);
}
createBooking('BH123'),
    createBooking('LH123', 3, 290);
createBooking('LH123', 2);
createBooking('LH123', undefined, 1000);
console.log('----------------');
const flight = 'LH345';
const joans = {
    name: 'Jonas',
    passport: 245262364363,
};
const checkIn = function (flightNum, passenger) {
    flightNum = 'LHUsdf';
    passenger.name = 'Mr.' + passenger.name;

    if (passenger.passport === 2456798) {
        alert('Checked in');
    } else {
        alert('Wrong passport!');
    }
};

checkIn(flight, joans);
console.log(flight);
console.log(joans);
//Khi chúng ta truyền 1 kiểu tham chiếu cho 1 hàm,
//những gì được sao chép thực sự chỉ là một tham 
//chiếu đến đối tượng trong heap bộ nhớ


//truyền theo giá trị và chuyển theo tham chiếu, JS không có chuyển tham chiếu mà là truyền theo giá trị, mặc dù có vẻ như nó đang truyền qua tham chiếu 
console.log('---------CallBack function------------');
const oneWord = function (str) {
    return str.replace(/ /g, '').toLowerCase();
};

const upperFirstWord = function (str) {
    const [first, ...orther] = str.split(' ');//Hủy cấu trúc
    return [first.toUpperCase(), ...orther].join(' ');//khi join() thì các biến phai ở trong một hàm 
};

//Higher-order function
const transformer = function (str, fn) {
    console.log(`Original string: ${str}`);
    console.log(`Transformed by: ${fn(str)}`);
    console.log(`Transformed by: ${fn.name}`);//name là tên hàm
};
transformer('JavaScript is the best!', upperFirstWord);
transformer('JavaScript is the best!', oneWord);

console.log('VÍ DỤ Tiếp');
const hight5 = function () {
    console.log('HIHI');
};
document.addEventListener('click', hight5);

// ['Jonas', 'Martha', 'Adam'].forEach(hight5);

console.log('----Ví dụ---');
function greeting(greeting) {
    return function (name) {//hàm con
        console.log(`${greeting} ${name}`);
    };
};
//Gán hàm con(là kết quả trả về của greeting('Hey')) cho biến greeterHey:
const greeterHey = greeting('Hey');
greeterHey('Jonas');//log ra Hey Jonas
greeterHey('Steven');//log ra Hey Steven

console.log('---------Cải tiến ví dụ bằng hàm mũi tên---------');
const greetArr = greeting => name => console.log(`${greeting} ${name}`);
greetArr('Hi')('Jonas');
console.log('--------Ví dụ---------');
//Đối tượng gốc
const lufthansa = {
    airline: 'Lufthansa',
    iataCode: 'LH',
    bookings: [],
    // book: function(){
    book(flightNum, name) {
        console.log(
            `${name} booked a seat on ${this.airline}
            flight ${this.iataCode} ${this.iataCode} ${flightNum}`
        );
        this.bookings.push({ flight: `${this.iataCode} ${flightNum}`, name });//truyền vào 1 đối tượng
    },
};
// lufthansa.booking(249, 'Jonas Schedtman');
lufthansa.book(129, 'Jonas Smith');
console.log(lufthansa);
//Đối tượng
const eurowings = {
    name: 'Eurowings',
    iataCode: 'EW',
    bookings: [],
};
const book = lufthansa.book;//hàm thôi, chưa có gọi hàm
// book(23, 'Sarah williams');//Sai
book.call(eurowings, 239, 'Sarah Williams');//call(hàm, đối số của hàm gốc)
//hàm này làm cho hàm book được gọi nhưng với ngữ cảnh của euorowings. Kết quả thông điệp xác nhận đặt chỗ cho Sarah williams trên chuyến bay của eurowings và thông tin đặt chỗ được thêm vào mảng bookings của Eurowings
console.log(eurowings);//
//Hàm chỉ là đối tượng, các đối tượng
//xác nhận đặt chỗ cho Mary Cooper trên chuyến bay của Lufthansa và thông tin đặt chỗ được thêm vào mảng bookings của Lufthansa
book.call(lufthansa, 329, 'Mary Cooper');
//từ khóa this bên trong lệnh gọi hàm thành lufthansa
console.log(lufthansa);
//Đối tượng
const swiss = {
    name: 'Swiss Air Lines',
    iataCode: 'LX',
    bookings: []
};

book.call(swiss, 580, 'Mary Cooper');
console.log(swiss);
// book.call(swiss, ...flight);
console.log('Thay vì truyền từng tham số thì mình truyền hết');
const flightData = [534, 'George Cooper'];

book.call(swiss, ...flightData);
console.log(swiss);//gọi hàm, vì đã truyền xong r// => Bookings có 2 mảng

//Bind method
console.log('---------The bind method---------');
// book.call(eurowings, 239, 'Sarah Williams');//call(hàm, đối số của hàm gốc)

//phương thức bind để tạo một hàm mới với từ khóa this cũng được đặt thành eurowings 
const bookEW = book.bind(eurowings);
const bookLH = book.bind(lufthansa);
const bookLX = book.bind(swiss);
bookEW(23, 'Steven Williams');
//Ứng dụng từng phần nghĩa là một phần của đối số của 1 hàm ban đầu đã được áp dụng, vì vậy nghĩa là đã được thiết lập 

const bookEW23 = book.bind(eurowings, 23);
bookEW23('Jonas Schemdtman');
bookEW23('Martha Cooper');

console.log('---------with Event---------');
console.log('// Sử dụng bind để gắn phương thức greet với obj');
lufthansa.planes = 300,//thuộc tính mới
    lufthansa.buyPlane = function () {
        console.log(this);
        this.planes++;
        console.log(this.planes);
    };
document.querySelector('.buy').addEventListener('click', lufthansa.buyPlane.bind(lufthansa));

//Partial application
const addTax = (rate, value) => value + value + rate;
console.log(addTax(10, 100));

const addVat = addTax.bind(null, 0.23);
// addVat = value => value + value * 0.23;

console.log(addVat(100));
console.log(addVat(23));

const addTaxRate = function (rate) {
    return function (value) {
        return value + value * rate;
    }
}
const addVat2 = addTaxRate(0.23);
console.log(addVat2(100));
console.log(addVat2(23));

