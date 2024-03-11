document.querySelectorAll('.counter').forEach(function(counter) {
  const input = counter.querySelector('input');
  const decreaseBtn = counter.querySelector('.decrease');
  const increaseBtn = counter.querySelector('.increase');

  decreaseBtn.addEventListener('click', function(e) {
    e.preventDefault();
    input.value = Math.max(0, parseInt(input.value, 10) - 1);
  });

  increaseBtn.addEventListener('click', function(e) {
    e.preventDefault();
    input.value = parseInt(input.value, 10) + 1;
  });
});