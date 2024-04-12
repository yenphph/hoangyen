const modal = document.querySelector('.modal');
const overlay = document.querySelector('.overlay');
const btnCloseModal = document.querySelector('.btn--close-modal');
const btnsOpenModal = document.querySelectorAll('.btn--show-modal');
const nav = document.querySelector('.nav');
const tabs = document.querySelectorAll('.operations__tab');
const tabsContainer = document.querySelector('.operations__tab-container');
const tabsContent = document.querySelectorAll('.operations__content');
//Selecting, creating, and deleting elements
console.log(document.documentElement);
console.log(document.head);
console.log(document.body);

const header = document.querySelector('.header');
// document.querySelectorAll('.section');
const allSections = document.querySelectorAll('.section');
console.log(allSections);

document.getElementById('section--1');
const allButtons = document.getElementsByTagName('button');//nhận nút
console.log(allButtons);

document.getElementsByClassName('btn');

const message = document.createElement('div');//tạo div
message.classList.add('cookie-message');
// message.textContent = `We use cookied for improved funstion`;
message.innerHTML = 'We use cookied for improved function and hihih sdf. <button class="btn btn--close-cookie">Got it!</button>';
header.prepend(message);//thêm vào đầu 
header.append(message);//thêm vào cuối

document.querySelector('.btn--close-cookie')
    .addEventListener('click', function () {
        message.remove();
    });
header.before(message);
document.querySelector('.btn--close-cookie')
    .addEventListener('click', function () {
        // message.remove();
        // message.parentElement.removeChild(message);//xóa phần tử con khỏi header
    });

    console.log('------------Styles, Attributes and Classes---------------');

message.style.backgroundColor = '#37383d';
message.style.width = '120%';

console.log(message.style.height);
console.log(message.style.backgroundColor);
//ấn đỏ hiện ra màu xanh
document.documentElement.style.setProperty('--color-primary', 'orangered');

console.log(getComputedStyle(message).color);//hiện màu sắc của nó ở đây
console.log(getComputedStyle(message).height);//hiện chiều cao của nó ở đây
message.style.height = Number.parseFloat(getComputedStyle(message).
height, 10) + 40 + 'px';//thay đổi chiều cao

//Atributes
const logo = document.querySelector('.nav__logo');
console.log(logo.alt);//nhận alt của ảnh
console.log(logo.src);//nhận đường dẫn
console.log(logo.className);//nhận tên classname
logo.alt = 'Beautiful minimalist'// đổi tên alt
console.log(logo.getAttribute('designer'));//Join
logo.setAttribute('company', 'Bankist');

console.log(logo.src);//link ảnh web
console.log(logo.getAttribute('src'));//tên src

const link = document.querySelector('.nav__link--btn');
console.log(link.href);
console.log(link.getAttribute('href'));

//class
console.log(logo.classList.add('c', 'j'));//undefined
console.log(logo.classList.remove('c', 'j'));//undefined
logo.classList.toggle('c');// Phương thức này thêm lớp 'c' vào phần tử nếu nó chưa tồn tại, và loại bỏ nó nếu đã tồn tại. Nói cách khác, nếu lớp 'c' đã được áp dụng cho phần tử, nó sẽ bị gỡ bỏ. Nếu chưa, nó sẽ được thêm vào.
console.log(logo.classList.contains('c'));
//
logo.className = 'jonos';
console.log(logo.className);
console.log('-----Thực hiện thao tác cuộn------');
const btnScrollTo = document.querySelector('.btn--scroll-to');
const section1 = document.querySelector('#section--1');
btnScrollTo.addEventListener('click', function(e){
    const s1coords = section1.getBoundingClientRect();
    // console.log(s1coords);//Mỗi khi cuộn rồi ấn vào cái mình click là sẽ biết được vị trí cái mình click

    // console.log(e.target.getBoundingClientRect());// e.target là một thuộc tính của đối tượng sự kiện
    // console.log('Current scroll (X/Y)', window.pageXOffset, pageYOffset);

    section1.scrollIntoView({behavior: 'smooth'});//cuộn qua trang
});
console.log('----------Event-----------');
const h1 = document.querySelector('h1');
const alerH1 =  function(e){
    e.preventDefault();
    alert('addEventListener: Greate! You are reading the heading:D');
};
h1.addEventListener('mouseenter', alerH1);
//nó kích hoạt khi chuột đi vaò phần tử nhất định
//Sau 3 giây nó loại bỏ hàm alerH1
setTimeout(() => h1.removeEventListener('mouseenter', alerH1), 3000);
