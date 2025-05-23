import http from './fetch.js';

function loadSurveys() {
  const surveys = document.getElementById("surveys");
  const template = document.querySelector("#survey-template");
  http.get('/api/survey/byUserId/' + localStorage.getItem("userId"))
    .then(response => response.json())
    .then(response => {
      surveys.innerHTML = '';
      response.forEach(survey => {
        const clone = template.content.cloneNode(true);
        let span = clone.querySelectorAll("span");
        span[0].textContent = survey.title;
        span[1].textContent = ''; // subtitle
        span[2].textContent = survey.status;
        span[3].textContent = survey.questionsCount;
        let button = clone.querySelectorAll("a");
        button[0].href = '/edit-survey.html?id=' + survey.id;
        button[1].href = '/run-survey.html?id=' + survey.id;
        surveys.appendChild(clone);
      });
    });
}

document.body.onload = () => {
  loadSurveys();
};
