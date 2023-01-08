hljs.highlightAll()

const editBtn = document.querySelector('.edit-btn')
const editUrl = location.href.replace('view', 'edit')

editBtn.addEventListener('click', () => {
  location.href = editUrl
})