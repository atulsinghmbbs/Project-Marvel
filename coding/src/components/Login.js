import { GoogleLogin } from "react-google-login"


const clientId = "530760682193-sofqsmesd12559btv57726375g42ljit.apps.googleusercontent.com"


function Login() {

    const onSuccess = (res) => {
        console.log("Login success! current user:", res.profileObj);
    }

    const onFailure = (res) => {
        console.log("Login Failed!", res)
    }

    return (
        <div id="signButton">
            <GoogleLogin
                clientId={clientId}
                buttonText="Login"
                onSuccess={onSuccess}
                onFailure={onFailure}
                cookiePolicy={'single_host_origin'}
                isSignedIn={true}
            />

        </div>
    )
}



export default Login;