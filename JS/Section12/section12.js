//Converting and checking numbers
const account1 = {
    owner: "Jonas Schmedtmann",
    movements: [200, 455.23, -306.5, 25000, -642.21, -133.9, 79.97, 1300],
    interestRate: 1.2, // %
    pin: 1111,
  
    movementsDates: [
      "2019-11-18T21:31:17.178Z",
      "2019-12-23T07:42:02.383Z",
      "2020-01-28T09:15:04.904Z",
      "2020-04-01T10:17:24.185Z",
      "2020-05-08T14:11:59.604Z",
      "2020-07-26T17:01:17.194Z",
      "2020-07-28T23:36:17.929Z",
      "2020-08-01T10:51:36.790Z",
    ],
    currency: "EUR",
    locale: "pt-PT", // de-DE
  };
  
  const account2 = {
    owner: "Jessica Davis",
    movements: [5000, 3400, -150, -790, -3210, -1000, 8500, -30],
    interestRate: 1.5,
    pin: 2222,
  
    movementsDates: [
      "2019-11-01T13:15:33.035Z",
      "2019-11-30T09:48:16.867Z",
      "2019-12-25T06:04:23.907Z",
      "2020-01-25T14:18:46.235Z",
      "2020-02-05T16:33:06.386Z",
      "2020-04-10T14:43:26.374Z",
      "2020-06-25T18:49:59.371Z",
      "2020-07-26T12:01:20.894Z",
    ],
    currency: "USD",
    locale: "en-US",
  };
  
  const accounts = [account1, account2];
  
  /////////////////////////////////////////////////
  // Elements
//   const labelWelcome = document.querySelector(".welcome");
//   const labelDate = document.querySelector(".date");
//   const labelBalance = document.querySelector(".balance__value");
//   const labelSumIn = document.querySelector(".summary__value--in");
//   const labelSumOut = document.querySelector(".summary__value--out");
//   const labelSumInterest = document.querySelector(".summary__value--interest");
//   const labelTimer = document.querySelector(".timer");
  
//   const containerApp = document.querySelector(".app");
//   const containerMovements = document.querySelector(".movements");
  
//   const btnLogin = document.querySelector(".login__btn");
//   const btnTransfer = document.querySelector(".form__btn--transfer");
//   const btnLoan = document.querySelector(".form__btn--loan");
//   const btnClose = document.querySelector(".form__btn--close");
//   const btnSort = document.querySelector(".btn--sort");
  
//   const inputLoginUsername = document.querySelector(".login__input--user");
//   const inputLoginPin = document.querySelector(".login__input--pin");
//   const inputTransferTo = document.querySelector(".form__input--to");
//   const inputTransferAmount = document.querySelector(".form__input--amount");
//   const inputLoanAmount = document.querySelector(".form__input--loan-amount");
//   const inputCloseUsername = document.querySelector(".form__input--user");
//   const inputClosePin = document.querySelector(".form__input--pin");

  console.log(23 === 23.0);//true
  console.log(0.1 + 0.2);
  //chuỗi thành số
  console.log(Number('23'));//23
  console.log(+'23');//23
  //parsing
  console.log(Number.parseInt('30px'));//30
  console.log(Number.parseInt('e23'));//NaN
  //isNaN() kiểm tra nó không phải là số
  //isFinite() kiểm tra nó là số
  //isInteger() kiểm tra là một số nguyên
  //parseInt() đọc giá trị từ chuỗi
  //parseFloat đọc giá trị từ chuỗi
  console.log('-----------Math and Rounding------------');
console.log(Math.sqrt(25));//5
console.log(25 ** (1/2));//5//Căn bậc 2
console.log(8 ** (1/3));//2 Căn bậc 3

console.log(Math.max(5, 18, 23, 11, 2));//23
console.log(Math.max(5, 18, '23', 11, 2));//23
console.log(Math.max(5, 18, '23px', 11, 2));//NaN

console.log(Math.min(5, 18, 23, 11, 2));//2
console.log(Math.min(5, 18, '23', 11, 2));//2
console.log(Math.min(5, 18, '23px', 11, 2));//NaN

console.log(Math.trunc(Math.random() * 6) + 1);//1 -> 6
const randomInt = (min, max) => Math.trunc(Math.random() * (max -min) + 1) + min;
console.log(randomInt(10, 20));

console.log(Math.round(23.3));//23
console.log(Math.round(23.9));//24

console.log(Math.ceil(23.9));//24
console.log(Math.ceil(23.9));//24

console.log(Math.floor(23.9));//23
console.log(Math.floor(23.9));//23

//HỒng là số, Trắng là chuỗi
//toFixed(số thập phân) trả về chuỗi
console.log((2.7).toFixed(0));//3 
console.log((2.7).toFixed(3));//2.700

console.log(5 % 2);//1 chia lấy dư
console.log(5 / 2);//2.5

const diameter = 287_589_000_000;
console.log(diameter);//287589000000

console.log(Number('230_000'));//NaN
console.log(parseInt('230_000'));//230

console.log('-----Creating dates-------');
const now = new Date();
console.log(now);

console.log(new Date('Aug 02 2020'));
console.log(new Date('Feb 14 2024 21:08:07'));
console.log(new Date(account1.movementsDates[0]));
//năm tháng ngày giờ phút giây
const future = new Date(2037, 10, 19, 15, 23, 5);
console.log(future);
console.log(future.getFullYear());
console.log(future.getMonth());
console.log(future.getDate());
console.log(future.getDay());
console.log(future.getHours());
console.log(future.getMinutes());
console.log(future.getSeconds());
console.log(future.toISOString());
future.setFullYear(2040);//Đổi 
console.log(future);

