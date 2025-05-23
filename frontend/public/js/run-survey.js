import http from './fetch.js';
import ws from './ws.js';

function loadSurvey() {
  let surveyId = new URLSearchParams(document.location.search).get("id");
  ws.channel = surveyId;
  const surveyTitle = document.querySelector("h2");
  const questions = document.querySelector("#questions");
  const template = document.querySelector("#question-template");
  const optionTemplate = document.querySelector("#option-template");
  http.get('/api/survey/' + surveyId)
    .then(response => response.json())
    .then(survey => {
      if (!survey) {
        surveyTitle.textContent = "Survey not found";
        return;
      }
      surveyTitle.textContent = survey.title;
      document.querySelector("#link").value = 'http://devpac06:8080/survey.html?id='+survey.id;
      survey.questions.forEach(question => {
        const clone = template.content.cloneNode(true);
        let options = clone.querySelector('.question-options');
        let title = clone.querySelector("h5 span");
        title.textContent = question.text;
        question.options.forEach(option => {
          let optionClone = optionTemplate.content.cloneNode(true);
          let span = optionClone.querySelectorAll("span");
          span[0].textContent = option.text;
          span[1].textContent = 0;
          span[1].id = 'question-count-' + option.id;
          options.appendChild(optionClone);
        });
        question.votes.forEach(vote => {
          let span = document.querySelector("#question-count-" + vote.optionId);
          span.textContent = +span.textContent + +vote.count;
        });
        questions.appendChild(clone);
      });
    });
}

function onMessageReceived(message) {
  if (message.type === 'VOTE') {
    let span = document.querySelector("#question-count-" + message.optionId);
    span.textContent = +span.textContent + 1;
  }

}

document.body.onload = () => {
  loadSurvey();
  ws.onMessageReceived = onMessageReceived;
};
