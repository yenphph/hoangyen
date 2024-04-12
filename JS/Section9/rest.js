//Toán tử spread để mở rộng một mảng thành các phần riêng lẻ
const restaurent = {
    name: "Cassico Italiano",
    location: "Via Anglo Tavanti 23, Firenze, Italy",
    categories: ["Italian", "bruscheta", "Garlic Bread", "Caprese salad"],
    startMenu: ["focaccia", "Bruschetta", "Garlic Bread", "Capress Salad"],
    mainMenu: ["Pizza", "Pasta", "Risotto"],
  
    openingHours: {
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

      orderPizza: function(mainIngredient, ...otherInge){
          console.log(mainIngredient);
          console.log(otherInge);
      }

  };

 const [a, b, ...others] = [1, 2, 3, 4, 5];
 console.log(a, b, others);

const [pizza, , risotto, ...otherfood] = [...restaurent.mainMenu, ...restaurent.startMenu]
console.log(pizza, risotto, otherfood);

//Object
const {sat, ...weekdays} = restaurent.openingHours;
console.log(weekdays);
//function
const add = function(...number){
    console.log(number);
    let sum = 0;
    for(let i = 0; i < number.length; i++){
        sum += number[i];
        console.log(sum);
    }
}
add(2, 3);
add(5, 7, 8, 9);
add(1, 2, 3, 7, 8);

const x = [23, 5,7];
add(...x);

restaurent.orderPizza('mushroom', 'onion', 'olives', 'spinach');
