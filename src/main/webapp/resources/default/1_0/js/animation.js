function animate(opts) {

	var start = new Date;
	var delta = opts.delta || linear;

	var timer = setInterval(function() {
		var progress = (new Date - start) / opts.duration;

		if (progress > 1)
			progress = 1;

		opts.step(delta(progress));

		if (progress == 1) {
			clearInterval(timer);
			opts.complete && opts.complete();
		}
	}, opts.delay || 13);

	return timer;
}

function moveBottom(elem, delta, duration, start, stop) {
	var to = stop;

	animate({
		delay : 10,
		duration : duration || 1000,
		delta : delta,
		step : function(delta) {
			elem.style.bottom = to * delta + start + "px"
		}
	});
}

// ------------------ Delta ------------------

function elastic(progress) {
	return Math.pow(2, 10 * (progress - 1))
			* Math.cos(20 * Math.PI * 1.5 / 3 * progress);
}

function linear(progress) {
	return progress;
}

function quad(progress) {
	return Math.pow(progress, 2);
}

function quint(progress) {
	return Math.pow(progress, 5);
}

function circ(progress) {
	return 1 - Math.sin(Math.acos(progress));
}

function back(progress) {
	return Math.pow(progress, 2) * ((1.5 + 1) * progress - 1.5);
}

function bounce(progress) {
	for (var a = 0, b = 1, result; 1; a += b, b /= 2) {
		if (progress >= (7 - 4 * a) / 11) {
			return -Math.pow((11 - 6 * a - 11 * progress) / 4, 2)
					+ Math.pow(b, 2);
		}
	}
}

function makeEaseInOut(delta) {
	return function(progress) {
		if (progress < .5)
			return delta(2 * progress) / 2;
		else
			return (2 - delta(2 * (1 - progress))) / 2;
	}
}

function makeEaseOut(delta) {
	return function(progress) {
		return 1 - delta(1 - progress);
	}
}