'use strict';

// Data
const account1 = {
  owner: 'Jonas Schmedtmann',
  movements: [200, 450, -400, 3000, -650, -130, 70, 1300],
  interestRate: 1.2, // %
  pin: 1111,
};

const account2 = {
  owner: 'Jessica Davis',
  movements: [5000, 3400, -150, -790, -3210, -1000, 8500, -30],
  interestRate: 1.5,
  pin: 2222,
};

const account3 = {
  owner: 'Steven Thomas Williams',
  movements: [200, -200, 340, -300, -20, 50, 400, -460],
  interestRate: 0.7,
  pin: 3333,
};

const account4 = {
  owner: 'Sarah Smith',
  movements: [430, 1000, 700, 50, 90],
  interestRate: 1,
  pin: 4444,
};
const accounts = [account1, account2, account3, account4];

/////////////////////////////////////////////////
// Elements
const labelWelcome = document.querySelector('.welcome');
const labelDate = document.querySelector('.date');
const labelBalance = document.querySelector('.balance__value');
const labelSumIn = document.querySelector('.summary__value--in');
const labelSumOut = document.querySelector('.summary__value--out');
const labelSumInterest = document.querySelector('.summary__value--interest');
const labelTimer = document.querySelector('.timer');

const containerApp = document.querySelector('.app');
const containerMovements = document.querySelector('.movements');

const btnLogin = document.querySelector('.login__btn');
const btnTransfer = document.querySelector('.form__btn--transfer');
const btnLoan = document.querySelector('.form__btn--loan');
const btnClose = document.querySelector('.form__btn--close');
const btnSort = document.querySelector('.btn--sort');

const inputLoginUsername = document.querySelector('.login__input--user');
const inputLoginPin = document.querySelector('.login__input--pin');
const inputTransferTo = document.querySelector('.form__input--to');
const inputTransferAmount = document.querySelector('.form__input--amount');
const inputLoanAmount = document.querySelector('.form__input--loan-amount');
const inputCloseUsername = document.querySelector('.form__input--user');
const inputClosePin = document.querySelector('.form__input--pin');

/////////////////////////////////////////////////

// const displayMovements = function(movements){

// }
const checkDogs = function (DogsJulia, dogsKate) {
  const dogsJuliaCorrected = DogsJulia.slice();
  console.log(dogsJuliaCorrected);
  dogsJuliaCorrected.splice(0, 1);
  dogsJuliaCorrected.splice(-2);
  const dogs = dogsJuliaCorrected.concat(-2);
  console.log(dogs);
  dogs.forEach(function (dog, i) {
    if (dog >= 3) {
      console.log(`Dog number ${i + 1} is an adult , and is ${dog} years old`);
    } else {
      console.log(`Dog number ${i + 1} is still a puppy dog`);
    }
  })

};
checkDogs([3, 5, 2, 12, 7], [4, 1, 15, 8, 3]);
console.log('------Map() Method--------');
console.log('------C1--------');
const eurToUsd = 1.1;
const movements = [200, 450, -400, 3000, -650, -130, 70, 1300];
const green = movements.map(mov => mov * eurToUsd
);
console.log(movements);
console.log(green);
console.log('------C2--------');

const movementsUSDFor = [];
for (const mov of movements) {
  movementsUSDFor.push(mov * eurToUsd);
};
console.log(movementsUSDFor);
const movementsDescriptions = movements.map((mov, i, arr) => {
  if (mov > 0) {
    return `Movement ${i + 1}: You desposited ${mov}`;
  } else {
    return `Movement ${i + 1}: You withdrew ${Math.abs(mov)}`;
  }
});
console.log('----Dùng toán tử bậc 2-------');
const movementsDescriptions1 = movements.map((mov, i, arr) =>
  `Movement ${i + 1}: You desposited ${mov > 0 ? 'deposited' : 'widthdrew'} ${Math.abs}`
);

console.log('-------Filter------');
const a = [12, 13, -33, -32, 23, 123, -3];
const deposits = a.filter(mov => mov > 0);
console.log(a);
console.log(deposits);
console.log('-------Cách 2------');
const depositsFor = [];
for (const mov of movements) if (mov > 0) depositsFor.push(mov);
console.log(depositsFor);

console.log('------reduce method------');
const balance = movements.reduce((acc, cur, i, arr) =>
  acc + cur//acc giống tổng ban đầu//cur phần tử
  , 0);
console.log(balance);//3840
console.log('------Cách 2-------');

let balance2 = 0;
for (const mov of movements) balance2 += mov;
console.log(balance2);//3840

console.log('-------Ví dụ có cả sắp xếp-----------');
const displayMovements = function (acc, sort = false) {
  containerMovements.innerHTML = '';

  const movs = sort ? acc.movements.slice().sort((a, b) => a - b) : acc.movements;

  movs.forEach(function (mov, i) {
    const type = mov > 0 ? 'deposit' : 'withdrawal';

    const html = `
      <div class="movements__row">
        <div class="movements__type movements__type--${type}">${i + 1
      } ${type}</div>
      <div class="movements_date" >${dis}</div>
        <div class="movements__value">${mov}€</div>
      </div>
    `;

    containerMovements.insertAdjacentHTML('afterbegin', html);
  });
};

console.log('-------------------');
const calcDisplayBalance = ((mo) => {
  mo.balance = mo.reduce((acc, mov) => acc + mov, 0);
  labelBalance.textContent = `${mo.balance} EUR`;
});

console.log('-------Challenge2---------');
const calcAverageHumanAge = ages => {
  const humanages = ages.map(age => (age <= 2 ? 2 * age : 16 + age * 4));
  console.log(humanages);
  //filter để là điều kiện được nè
  const adults = humanages.filter(age => age >= 18);
  console.log(adults);
  const average = adults.reduce((acc, age, i, arr) => acc + age / arr.length, 0);
  return average;
};
console.log(calcAverageHumanAge([5, 2, 4, 1, 15, 8, 3]));
console.log(calcAverageHumanAge([16, 6, 10, 5, 6, 1, 4]));

console.log('---------Cách 2 của Challenge2 => Chanllenge 3-----------');
const secondChanllenge3 = ages =>
  ages.map(age => (age <= 2 ? 2 * age : 16 + age * 4))
    .filter(agec => agec >= 18)
    .reduce((acc, agec, i, arr) => acc + agec / arr.length, 0);

console.log(secondChanllenge3([5, 2, 4, 1, 15, 8, 3]));
console.log(secondChanllenge3([16, 6, 10, 5, 6, 1, 4]));

console.log('-----------------------');
//lọc ra các khoản tiền gửi

const totalDespositsUSD = movements
  .filter(mov => mov > 0)
  // .map(mov => mov * eurToUsd)
  .map((mov, i, arr) => {
    console.log(arr);
    return mov * eurToUsd
  })
  .reduce((acc, mov) => acc + mov, 0);
console.log(totalDespositsUSD);

console.log('--------------------');
const calcDisplaySummary = function (mov) {
  //hiện thị tiền gửi
  //lọc, tính
  const incomes = movements
    .filter(mov => mov > 0)
    .reduce((acc, mov) => acc + mov, 0);
  labelSumIn.textContent = `${incomes}`;
  //hiện thị tiền ra ngoài trên trình duyệt
  const out = movements.filter(mov => mov < 0)
    .reduce((acc, mov) => acc + mov, 0);
  labelSumOut.textContent = `${Math.abs(out)}`;
  //Math.abs( tên biến) xóa dấu hiệu '-'
  //tiền lãi được trả cho mỗi lần gửi tiền. Vì vậy trước khi chúng ta cần nhạn được những khoản tiền cần gửi này.
  //Và bây giờ trên mỗi khoản tiền gửi này, chúng tôi sẽ nhận được 1.2%
  //Tiền lãi sau mỗi lần gửi
  const interest = movements.filter(mov => mov > 0)
    .map(deposits => deposits * 1.2 / 100)
    //Nhớ kĩ map filter là vòng lặp
    .filter((int, i, arr) => {//lọc tiếp
      console.log(arr);//int giá trị, i số phần tử thứ mấy
      console.log(i);
      return int >= 1;
    })
    .reduce((acc, mov) => acc + mov, 0);
  labelSumInterest.textContent = `${interest}`;
};


console.log('---------find method-----------');
//phương thức find cũng chấp nhận điều kiện, và cũng giống như phương pháp lặp trên mảng
//Để lấy một phần tử mảng dựa trên một điều kiện 
const firsWithdrawal = movements.find(mov => mov < 0);
console.log(movements);
console.log(firsWithdrawal);
//first Filter trả về các phần tử phù hợp với điều kiện trong khi phương thức find chỉ trả về phần tử đầu tiên
const account = accounts.find(acc => acc.owner === 'Jessica Davis');
console.log(account);
//toán tử bình đăng ===
// var hihi ;
for (const accoun of accounts) {
  accoun.owner === 'Jessica Davis' ?
    console.log(accoun.owner) : '';
};
console.log('-----Computing uername------');
const user = 'Steven Thomas Williams';//stw
//thêm thuộc tính cho đối tượng trong mảng
const createUsernames = function (acc) {
  acc.forEach(function (accs) {
    accs.username = accs.owner
      .toLowerCase()
      .split(' ')
      .map(name => name[0])
      .join('');
    //hhh
  });
};
createUsernames(accounts);
console.log(accounts);
console.log('--------------------');
const updateUI = function (acc) {
  // Display movements
  displayMovements(acc.movements);

  // Display balance
  calcDisplayBalance(acc);

  // Display summary
  calcDisplaySummary(acc);
};
console.log('-----------login------------');
let currentAccount;
console.log('--------Hiện ngày tháng năm trên trình duyệt--------');
const now = new Date();
const day = `${now.getDate()}`.padStart(2, 0);
const month = `${now.getMonth() + 1}`.padStart(2, 0);
const year = now.getFullYear();
const hour = now.getHours();
const min = now.getMinutes();
labelDate.textContent = `${day}/${month}/${year}, ${hour} : ${min}`;


btnLogin.addEventListener('click', function (e) {
  e.preventDefault();//để có thể click
  currentAccount = accounts.find(acc => acc.username === inputLoginUsername.value);
  console.log(currentAccount);
  //?. tránh lỗi khi nó undefined
  if (currentAccount?.pin === Number(inputLoginPin.value)) {
    //hiện thị thông báo chào mừng
    labelWelcome.textContent = `Wellcome back, ${currentAccount.owner.split(' ')[0]}`;
    containerApp.style.opacity = 1;
    //Clear input fields
    inputLoginUsername.value = inputLoginPin.value = '';
    inputLoginPin.blur();
    //display movements
    displayMovements(currentAccount.movements);
    //hiện thị balance
    calcDisplayBalance(currentAccount.movements);
    //display  summary
    calcDisplaySummary(currentAccount.movements);
  }
});

console.log(accounts);
console.log('-------implementing transfers---------');
btnTransfer.addEventListener('click', function (e) {
  e.preventDefault();//ấn vào nút thì tải lại trang
  const amount = Number(inputTransferAmount.value);
  const receiverAcc = accounts.find(
    acc => acc.username === inputTransferTo.value
  );
  inputTransferAmount.value = inputTransferTo.value = '';

  // console.log(amount, receiverAcc);
  if (amount > 0 &&
    receiverAcc &&
    currentAccount.balance >= amount &&
    receiverAcc?.username !== currentAccount.username) {
    //thêm số tiền vào
    //doing the transfer
    //trừ đi số tiền tài khoản giao dịch giao dịch
    //CHÚ Ý: CHƯA TRỪ ĐƯỢC
    currentAccount.movements.push(-amount);
    //thêm số tiền vào tài khoản vừa giao dịch
    receiverAcc.movements.push(amount);
    //UPdateUI
    updateUI(currentAccount);
  }
});
console.log('------FindIndex-------');
//trả về phần tử được tìm thấy chứ không phải của chính phần tử đó

//xóa tài khoản
btnClose.addEventListener('click', function (e) {
  e.preventDefault();
  // console.log('Delete');
  if (inputCloseUsername.value === currentAccount.username &&
    Number(inputClosePin.value) === currentAccount.pin) {
    const index = accounts.findIndex(
      acc => acc.username === currentAccount.username);
    console.log(index);//CHƯA HIỆN CHỈ SỐ
    // accounts.splice(index, 1);
  };
  inputCloseUsername.value = inputClosePin.value = '';
});
console.log('-------some and every--------');
console.log(movements);
//kiểm tra xem mảng có bao gồm giá trị nào hay không
console.log(movements.includes(-130));
const any = movements.some(mov => mov > 0);
console.log(any);//true
//kiểm tra xem có cái nào trong đó có thảo mãn đk này không
console.log(movements.some(mov => mov === -130));//true
//
console.log('---------Every tất cả phàna tử trong mảng thỏa mãn hết thì true---------');
console.log(movements.every(mov => mov > 0));//false
console.log(account4.movements.every(mov => mov > 0));//true

console.log('------Separate Callback------');
const deposit = mov => mov < 0;
//gọi callback
console.log(movements.some(deposit));//true
console.log(movements.every(deposit));//false
console.log(movements.filter(deposit));// hiện mảng

console.log('-----Sử dụng some-------');
btnLoan.addEventListener('click', function (e) {
  e.preventDefault();
  const amount = Number(inputLoanAmount.value);
  if (amount > 0 && currentAccount.movements.some(mov => mov >= amount * 0.1)) {
    //add movement
    currentAccount.movements.push(amount);
    //Update UI
    updateUI(currentAccount);
  };
  inputLoanAmount.value = '';
});

console.log('-------flat and flatMap-------');
const arr = [[1, 2, 3], [4, 5, 6], 7, 8];
console.log(arr.flat());//gộp lại thành 1 mảng
const arrDeep = [[[1, 2], 3], [4, [5, 6]], 7, 8];
console.log(arrDeep.flat(2));//tạo thành 1 mảng
//flat()
const overlBalance1 = accounts
  .map(acc => acc.movements)
  .flat()
  .reduce((acc, mov) => acc + mov, 0);
console.log(overlBalance1);
//flatMap()
const overBalance2 = accounts
  .flatMap(acc => acc.movements)
  .reduce((acc, mov) => acc + mov, 0);
console.log(overBalance2);
console.log('-------Sorting array-------');
//string
const owner = ['Jonas', 'Zach', 'Adam', 'Martha'];
console.log(owner.sort());//sắp xếp theo bảng chữ cái
//number
console.log(movements);
//return < 0, A, B
//return > 0, B, A
//tăng dần C1
movements.sort((a, b) => {
  if (a > b) return 1//a - b sẽ luôn dương
  if (a < b) return -1;//a - b luôn âm
});
//tăng dần cải tiến C1
movements.sort((a, b) => a - b);

console.log(movements);
//giảm dần C1
movements.sort((a, b) => {
  if (a > b) return -1;
  if (a < b) return 1;
});
//giảm dần cải tiến C1
movements.sort((a, b) => b - a);
console.log(movements);
console.log('-------Thực hiện chức năng sắp xếp trong trình duyệt web-------');
let sorted = false;
btnSort.addEventListener('click', function (e) {
  e.preventDefault();
  displayMovements(currentAccount.movements, !sorted);
  sorted = !sorted;
});
console.log('-----ways of creating and filling arrays------');
[1, 2, 3, 4, 5, 6, 7];
console.log(new Array(1, 2, 3, 4, 5, 6, 7));
const x = new Array(7);
console.log(x);//tạo 1 mảng mới với 7 phần tử trống

x.fill(1, 3);//fill(số muốn điền, nơi bắt đầu điền, nơi kết thúc)
console.log(x); // [empty x 3, 1, 1, 1, 1]
//array.from() phương thức khởi tạo
const y = Array.from({ length: 7 }, () => 1);//trả về là 1 và sau đó đặt vào mỗi vị trí của mảng
console.log(y);

const z = Array.from({ length: 7 }, (_, i) => i + 1);
console.log(z);
//to mutate original array thay đổi mảng ban đầu
console.log('------------');
//.push (end) .unshift() (start)
//remove
//.pop() (end) .shift() (start) .splice (any)
//others
// .reverse (đảo ngược) .sort(sắp xếp) .fill(điền)
//a new aray muốn 1 mảng mới
console.log('------------');
//.map (nếu muốn tính toán một mảng, từ bản gốc, thì chúng ta sử dụng phương pháp bản đồ)
//filter (tạo một mảng mới bằng cách lọc lấy điều kiện)
//.slice .concat .flat .flatMap
//an array index muốn một chỉ mục
//.indexOf .finIndex tìm kiếm cho 1 phần tử trong
//an array element truy xuất toàn bộ phần tử mảng dưa trên đk thử nghiệm, mà chúng tôi cung cấp, trong hàm gọi lại
console.log('------------');
//.find
//know if aray includes nếu một mảng bao gômg, một phần tử nhất định
console.log('------------');
//.includes .some .every
// a new string muốn nhận chuỗi mới
console.log('------------');
//.join
//to transform to value biến đổi mảng thành một giá trị mới
console.log('------------');
//.reduce 
//to just loop array lặp qua mảng
console.log('------------');
//forEach lặp không tạo ra giá trị mới
console.log('-------Thực hành phương pháp mảng-------');
// 1.
const bankDeposiSum = accounts
  .flatMap(acc => acc.movements)
  .filter(mov => mov)
  .reduce((sum, cur) => sum + cur, 0);
console.log(bankDeposiSum);

// 2.
const numDeposits1000 = accounts
  .flatMap(acc => acc.movements)
  .reduce((count, cur) => (
    cur >= 1000 ? ++count : count
  ), 0);
console.log(accounts.flatMap(acc => acc.movements));
console.log(numDeposits1000);

// 3.
const { depositt, withdrawal } = accounts
  .flatMap(acc => acc.movements)
  .reduce((sums, cur) => {
    // cur > 0 ? (sums.depositt += cur) : (sums.withdrawal += cur);
    sums[cur > 0 ? 'depositt' : 'withdrawal'] += cur;
    return sums;
  }, { depositt: 0, withdrawal: 0 });
console.log(depositt, withdrawal);

// 4.
const converTitleCase = function (title) {
  const exceptions = ['a', 'an', 'the', 'but', 'or', 'in', 'with'];
  const titleCase = title
    .toLowerCase()
    .split(' ')
    .map(word => exceptions.includes(word) ? word : word[0].toUpperCase() + word.slice(1)).join(' ');
  return titleCase;
};
console.log(converTitleCase('this is a nice title'));
console.log(converTitleCase('and is a LONG title but no too long'));
console.log('------Challenge 4-------');
const dogs = [
  { weight: 22, curFood: 250, owers: ['Alice', 'Bob'] },
  { weight: 8, curFood: 200, owers: ['Matilda'] },
  { weight: 13, curFood: 275, owers: ['Sarah', 'John'] },
  { weight: 32, curFood: 240, owers: ['Michael'] },
];

// 1.
dogs.forEach(dog => (//Math.trunc trả về số nguyên
  dog.recFood = Math.trunc(dog.weight ** 0.75 * 28)));
console.log(dogs);

// 2.

const dogSarah = dogs.find(dog => dog.owers.includes('Sarah'));
console.log(dogSarah);
console.log(`Sarah's dog is eating ${dogSarah.curFood > dogSarah.recFood ? 'much' : 'little'}`);

// 3.
const ownerEatToMuch = dogs
  .filter(dog => dog.curFood > dog.recFood)
  .flatMap(dog => dog.owers);//lấy tên chủ
console.log(ownerEatToMuch);

const ownerEatTooLittle = dogs
  .filter(dog => dog.curFood < dog.recFood)
  .flatMap(dog => dog.owers);
console.log(ownerEatTooLittle);

// 4.
console.log(`${ownerEatToMuch.join(' and ')}'s dogs eat too much`);
console.log(`${ownerEatTooLittle.join(' and ')}'s dogs eat too little`);

// 5.
console.log(dogs.some(dog => dog.curFood === dog.recFood));//false

// 6.
const checkEatingOkay = dog => dog.curFood > dog.recFood * 0.9 && dog.curFood < dog.recFood * 1.1;
console.log(dogs.some(checkEatingOkay));

// 7.
console.log(dogs.filter(checkEatingOkay));//Chưa thâý ra kết quả

//8.
const dogsCopy = dogs.slice().sort((a, b) => a.recFood - b.recFood);
console.log(dogsCopy);

console.log('------Challenge 12----------');//Chưa ra kết quả
labelBalance.addEventListener('click', function () {
  [...document.querySelectorAll('.movements_row')]
    .forEach(function (row, i) {
      //0, 2, 4, 6
      if (i % 2 === 0) row.style.backgroundColor = 'orangered';
      //0, 3, 6, 9
      if(i % 3 === 0) row.style.backgroundColor = 'blue';
    });
});
