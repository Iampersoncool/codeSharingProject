import { editor } from "./script.js"

const saveBtn = document.querySelector('.save-btn')

saveBtn.addEventListener('click', async () => {
  try {
    const token = getContentAttribute('meta[name="_csrf"]')
    const header = getContentAttribute('meta[name="_csrf_header"]')
    
    const response = await fetch('/save', {
       method: 'POST',
       headers: {
         [header]: token,
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({
         code: editor.getValue()
       })
    })
     
     const uuidText = await response.json()
     location.href = `/view/${uuidText}`
  } catch (e) {
    console.error(e)
    alert('There was an error saving.')
  }
})

function getContentAttribute(selector, attrName) {
  return document.querySelector(selector).getAttribute('content')
}