/**
 * @returns cross-browser AJAX-Object
 */
function getXmlHttpRequest() {
	if (window.XMLHttpRequest) {
		try {
			return new XMLHttpRequest();
		} catch (e) {
		}
	} else if (window.ActiveXObject) {
		try {
			return new ActiveXObject('Msxml2.XMLHTTP');
		} catch (e) {
		}
		try {
			return new ActiveXObject('Microsoft.XMLHTTP');
		} catch (e) {
		}
	}
	return null;
}

function parseXml(str) {
	if (window.ActiveXObject) {
		var doc = new ActiveXObject('Microsoft.XMLDOM');
		doc.loadXML(str);
		return doc;
	} else if (window.DOMParser) {
		return (new DOMParser).parseFromString(str, 'text/xml');
	} else
		return "";
}

/**
 * Represent pop-up message with status report information.
 * 
 * @param message
 *            your message, what would you like to see on bottom of screen
 * @param status
 *            "ok" || "error"
 */
function msg(message, status) {
	var owner = document.getElementById('navigation');

	var msg = document.createElement('DIV');
	switch (status) {
	case "ok":
		msg.className = "msg msg-ok";
		break;
	case "error":
		msg.className = "msg msg-error";
		break;
	}

	if (!message) {
		message = "Empty message... Sorry something going wrong. Kill you self, please.";
		msg.className = "msg msg-error";
	}

	var left = document.createElement('SPAN');
	left.className = "left";
	left.appendChild(document.createTextNode(message));

	var right = document.createElement('SPAN');
	right.className = "right";
	right.onclick = function() {
		owner.removeChild(msg);
	}

	msg.appendChild(left);
	msg.appendChild(right);

	owner.appendChild(msg);
	moveBottom(msg, makeEaseOut(back), 1000, -30, 30);
}

function appendHeaders(table, headers) {
	var tr = document.createElement('tr');
	for (var i = 0; i < headers.length; i++) {
		var th = document.createElement('th');
		th.appendChild(document.createTextNode(headers[i]));
		tr.appendChild(th);
	}
	table.appendChild(tr);
}

function clean(elem) {
	while (elem.hasChildNodes()) {
		elem.removeChild(elem.lastChild);
	}
}

function createModule(id) {
	var req = getXmlHttpRequest();
	var title = document.getElementById("moduleTitle").value;
	var discipline_id = document.getElementById("moduleDiscipline").value;

	req.onreadystatechange = function() {
		if (req.readyState != 4)
			return;
		msg(req.responseText, "ok");
		loadModule(id)
	}

	req.open("GET", "teacher/create?id=" + id + "&title=" + title
			+ "&discipline_id=" + discipline_id, true);
	req.send(null);
}

function loadModule(id) {
	var req = getXmlHttpRequest();

	var table = document.getElementById("tableModule");
	clean(table);
	appendHeaders(table, [ "id", "Title", "Date", "Discipline",
			"Content Control" ])

	/* table.style.backgroundImage = "url(./images/small-ajax-loader.gif)"; */

	req.onreadystatechange = function() {
		if (req.readyState != 4)
			return;

		var text = req.responseText;

		if (!text)
			msg("Empty Table Module. Please, add new module.", "error");

		var line = text.split("\n");

		for (var i = 0; i < line.length; i++) {
			if (line[i] == '')
				continue;
			var tr = document.createElement('tr');

			var chunks = line[i].split(':');
			for (var j = 0; j < chunks.length; j++) {
				appendTD(tr, chunks[j])
			}
			appendContentControl(tr, chunks[0]);

			// I just try copy loop variable chunks[0]. I think javascript
			// choose asspath...
			tr.onclick = function(variable) {
				return function() {
					loadCurrentModule(variable);
				}
			}(chunks[0]);

			table.appendChild(tr);
		}
	}

	req.open("GET", "teacher/loadModule?id=" + id, true);
	req.send(null);
}

function loadCurrentModule(id) {
	var req = getXmlHttpRequest();

	req.onreadystatechange = function() {
		if (req.readyState != 4)
			return;

		var rawXml = req.responseText;

		console.log(rawXml);// TEMPORARY

		var table = document.getElementById("tableCurrentModule");
		clean(table);
		appendHeaders(table, [ "id", "question", "difficult", "answer",
				"correct", "Content Control" ]);

		var root = parseXml(rawXml).documentElement;

		var questions = root.getElementsByTagName("question");
		for (var i = 0; i < questions.length; i++) {

			var answers = questions[i].getElementsByTagName("answer");
			for (var j = 0; j < answers.length; j++) {

				var tr = document.createElement('tr');

				if (j === 0) {
					appendTD(tr, questions[i].getAttribute("id"),
							answers.length);
					appendTD(
							tr,
							questions[i].getElementsByTagName("content")[0].childNodes[0].data,
							answers.length);
					appendTD(tr, questions[i].getAttribute("difficult"),
							answers.length);
				}
				appendTD(tr, answers[j].childNodes[0].data);
				appendTD(tr, answers[j].getAttribute("correct"));
				if (j === 0) {
					appendContentControl(tr, questions[i].getAttribute("id"),
							answers.length);
				}
				tr.onclick = function(variable) {
					return function() {
						msg(variable, "ok");
					}
				}(questions[i].getAttribute("id"));
				table.appendChild(tr);
			}
		}
	}

	req.open("GET", "teacher/loadCurrentModule?module_id=" + id, true);
	req.send(null);
}

function appendTD(tr, txt, rowspan) {
	var td = document.createElement('td');
	td.rowSpan = rowspan || 1;
	td.appendChild(document.createTextNode(txt));
	tr.appendChild(td);
}

function appendContentControl(tr, msgVar, rowspan) {
	var tdControl = document.createElement('td');
	tdControl.rowSpan = rowspan || 1;

	var del = document.createElement('span');
	del.appendChild(document.createTextNode("DELETE"));
	del.className = "ico del";
	del.onclick = function(variable) {
		return function() {
			msg("DEL " + variable, "ok");
		}
	}(msgVar);

	var edit = document.createElement('span');
	edit.appendChild(document.createTextNode("EDIT"));
	edit.className = "ico edit";
	edit.onclick = function(variable) {
		return function() {
			msg("EDIT " + variable, "ok");
		}
	}(msgVar);

	tdControl.appendChild(del);
	tdControl.appendChild(edit);
	tr.appendChild(tdControl);
}

function createQuestion(idModule) {
	var req = getXmlHttpRequest();
	var content = document.getElementById("questionContent").value;
	var difficult = document.getElementById("questionDificult").value;

	req.onreadystatechange = function() {
		if (req.readyState != 4)
			return;

		msg(req.responseText, "ok");
		loadCurrentModule(idModule)
	}

	req.open("GET", "teacher/createQuestion?id=" + id + "&title=" + content
			+ "&difficult=" + difficult, true);
	req.send(null);
}

function check(form) {
	
	var input = form[form.id + ":input"].value;
	
	if (input > 0) {
		form.submit();
	} else {
		alert("input = " + input);
	}
}
