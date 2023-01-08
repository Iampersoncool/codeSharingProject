const editorElement = document.querySelector('.editor')
const editElement = document.querySelector('.editCode')

let lineNumbers = true

export const editor = CodeMirror.fromTextArea(editorElement, {
  lineNumbers,
  theme: 'solarized dark'
})

if (editElement !== null) {
  localStorage.setItem('editorCode', editElement.value) 
}

editor.setSize('100vw', '100vh')

const code = localStorage.getItem('editorCode')
code && editor.setValue(code)

editor.on('change', () => {
  localStorage.setItem('editorCode', editor.getValue())
})

document.addEventListener('keyup', (e) => {
  if (e.altKey && e.key === 'n') {
    lineNumbers = !lineNumbers
    editor.setOption('lineNumbers', lineNumbers)
  }
})
