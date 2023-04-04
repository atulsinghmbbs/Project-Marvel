

import Nav from './components/Nav';
import LogoutButton from "./components/Logout";
import LoginButton from "./components/Login";
import { useEffect } from 'react';
import { gapi } from "gapi-script";




const clientId = "530760682193-sofqsmesd12559btv57726375g42ljit.apps.googleusercontent.com"

const App = () => {

  useEffect(() => {
    function start() {
      gapi.clientId.init({
        clientId: clientId,
        scope: ""
      })
    }
    gapi.load("client:auth2", start)
  })

  return (
    <div>

      <Nav />

      <LoginButton />
      <LogoutButton />



    </div>
  )
}

export default App