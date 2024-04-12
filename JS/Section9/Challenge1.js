'use strict';
const game = {
    team1: 'Bayern Munich',
    team2: 'Borrussia Dortmud',
    players: [
        [
            'Never',
            'Pavard',
            'Martinez',
            'Alaba',
            'Davies',
            ' Kimmich',
            'Goretzka',
            '  Coman',
            'Muller',
            ' Gnarby',
            'Lewandowski',
        ],
        [
            'Burki',
            'Schulz',
            'Hummels',
            'Akanji',
            'Hakimi',
            'Weigl',
            '  Witsel',
            'Hazard',
            'Brandt',
            ' Sancho',
            'Gotze'
        ],
    ],
    score: '4:01',
    scored: ['Lewandowski', 'Gnarby', 'Lewandowski', 'Hummels'],
    date: 'Nov 9th, 2037',
    odds: {
        team1: 1.33,
        x: 234,
        tea2: 6.5,
    },
}
//1
const [player1, player2] = game.players;
console.log(player1, player2);

// 2.
const [gk, ...fileldPlayers] = player1;
console.log(fileldPlayers);

//3.
const allPlayers = [...player1, ...player2];
console.log(allPlayers);

//4.
const players1Final = [...player1, 'Thiage', 'Cationa', 'Chihgfsdf'];
console.log(players1Final);

//5.
const {odds : {team1, x: draw, tea2}} = game;
console.log(team1, draw, tea2);

//6.
const printGoals = (...players) => {
    console.log(players);
    console.log(`${players.length} goals were scored`);
}
// printGoals(1, 2, 3, 4, 5, 6, 7, 6);
printGoals(...game.score);

//7.
team1 < tea2 && console.log('Team 1 is more likely to win');
tea2 < team1 && console.log('Team 2 is more likely to win');

// 1.
console.log('-------------------')
for(const [i, player] of game.scored.entries()){
    console.log(`Goal ${i + 1}: ${player}`);//key, value
}

// 2.
const odds = Object.values(game.odds);
let average = 0;
for(const odd of Object.values(game.odds)){
    console.log(average);
    average += odd;
    average /= odds.length;
    console.log(average)
}
console.log('---------Challenge 3-----');
const gameEvents = new Map([
    [17,'GOAL'],
    [36,'Substitution'],
    [47,'GOAL'],
    [61,'Substitution'],
    [64, 'Yellow card'],
    [69,'Red card'],
    [70, 'Substitution'],
    [72, 'Substitution'],
    [75, 'GOAL'],
    [80, 'GOAL'],
    [92, 'Yellow card'],
]);
//1.
// console.log(gameEvents.values());
const events = [...new Set(gameEvents.values())];
console.log(events);

// 2.
gameEvents.delete(64);

// 3.

//4. Lặp toàn bộ mảng sự kiện và in ra màn hình, đánh dấu đối với mỗi sự kiện trong
//trận đấu diễn ra trong hiệp 1 hay hiệp 2,
for( const [min, event] of gameEvents){
    const half = min <= 45 ? 'First' : 'Second';
    console.log(`[${half} Half] ${min} : ${event}`);
}
console.log('-----------Challenge4--------');
document.body.append(document.createElement('textarea'));
document.body.append(document.createElement('button'));

// document.querySelector('button').addEventListener('click', function(){
//     const text = document.querySelector('textarea').value;
//     console.log
// })