import { GoogleLogout } from "react-google-login"

const clientId = "530760682193-sofqsmesd12559btv57726375g42ljit.apps.googleusercontent.com"




function Logout() {

    const onSuccess = () => {
        console.log("log out successfully")
    }

    return (
        <div id="signOutButton">
            <GoogleLogout
                clientId={clientId}
                buttonText={"Logout"}
                onLogoutSuccess={onSuccess}
            />
        </div>
    )
}

export default Logout;