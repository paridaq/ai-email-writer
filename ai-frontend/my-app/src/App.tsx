import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const handlelogin = ()=>{
    window.location.href="http://localhost:8080/oauth2/authorization/google"
  }

  return (
    <>
     <div>
      <button>log in with google</button>
     </div>
    </>
  )
}

export default App
