import React, {useState} from "react";
import PageHeader from "../components/PageHeader";
import "../styles/LoginPage.css"
import FormInput from "../components/FormInput";
import LoginRegistrationRedirectBlock from "../components/LoginRegistrationRedirectBlock";

const LoginPage = () => {
    const [values, setValues] = useState({
        email: "",
        password: ""
    })

    const inputProps = [
        {
            id: 1,
            name: "email",
            type: "text",
            placeholder: "Email",
            label: "Email",
            errorMessage: "Incorrect email",
            pattern: "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            required: true
        },
        {
            id: 2,
            name: "password",
            type: "password",
            placeholder: "Password",
            label: "Password",
            errorMessage: "Input your password",
            required: true
        }
    ]

    const handleSubmit = (e) => {
        e.preventDefault();
        //TODO
        // here is the code that should send a request to api
    }

    const onChange = (e) => {
        setValues({...values, [e.target.name]: e.target.value})
    }

    console.log(values)
    return (
        <div className={"LoginPage"}>
            <PageHeader/>
            <br/>
            <form className={"LoginForm"} onSubmit={handleSubmit}>
                <h2 className={"LoginPageFormHeader"}>Login</h2>
                {inputProps.map((inputProp) => (
                    <FormInput
                        {...inputProp}
                        value={values[inputProp.name]}
                        onChange={onChange}
                    />
                ))}
                <button>Submit</button>
                <span>Don't have an account? Register</span>
            </form>
        </div>
    );
}

export default LoginPage;