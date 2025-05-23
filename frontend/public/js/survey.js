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
      survey.questions.forEach(question => {
        const clone = template.content.cloneNode(true);
        let options = clone.querySelector('.question-options');
        let title = clone.querySelector("h5 span");
        title.textContent = question.text;
        question.options.forEach(option => {
          let optionClone = optionTemplate.content.cloneNode(true);
          let label = optionClone.querySelector("label");
          label.textContent = option.text;
          label.htmlFor = 'option-' + option.id;
          let radio = optionClone.querySelector("input");
          radio.id = 'option-' + option.id;
          radio.name = 'radios-' + question.id;
          radio.onchange = () => vote(surveyId, option.id);
          options.appendChild(optionClone);
        });
        questions.appendChild(clone);
      });
    });
}

function vote(surveyId, optionId) {
  ws.client.publish({ destination: '/app/survey/' + surveyId, body: JSON.stringify({
    type: 'VOTE',
    userId: localStorage.getItem("userId"),
    optionId: optionId
  })});
}

document.body.onload = () => {
  loadSurvey();
};
