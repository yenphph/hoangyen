const poll = {
    question: 'What is your favourite programming language?',
    option: ['0: JavaScript', '1: Python', '2: Rust', '3: C++'],
    answers: new Array(4).fill(0),
    registerNewAnswer(){
        //get answer
        const answer = Number(prompt(`${this.question} \n
        ${this.option.join('\n')}\n
        (Write option number)`));
        console.log(answer);
        //
        typeof answer === 'number' && answer < this.answers.length && this.answers[this.answers]++;
        // console.log(this.answers);
        this.displayResults();
        this.displayResults('string');
    },
    displayResults(type = 'array'){
        if(type === 'array'){
            console.log(this.answers);
        }else if(type === 'string'){
            console.log(`Poll ${this.answers.join(', ')}`);
        }
    },
};
// poll.registerNewAnswer();
document.querySelector('.poll').addEventListener('click', poll.registerNewAnswer.bind(poll));
//this trỏ đến phần tử queryS...//Từ khóa này luôn trỏ đến phần tử mà nó được gắn vào 