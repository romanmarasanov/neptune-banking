import PageHeader from "../components/PageHeader";
import FormInput from "../components/FormInput";
import LoginRegistrationRedirectBlock from "../components/LoginRegistrationRedirectBlock"
import {useState} from "react";
import React from "@types/react";

const RegistrationPage = () => {
    const [values, setValues] = useState({
        firstName: "",
        secondName: "",
        email: "",
        phoneNumber: "",
        password: "",
        confirmPassword: "",
    })

    const inputProps = [
        {
            id: 1,
            type: "text",
            name: "firstName",
            placeholder: "First Name",
            label: "First Name",
            errorMessage: "Name should contains at least two characters",
            pattern: "[A-Za-z]{2,}",
            required: true
        },
        {
            id: 2,
            type: "text",
            name: "secondName",
            placeholder: "Second name",
            label: "Second name",
            errorMessage: "Second name should contain at least two characters",
            pattern: "[A-Za-z]{2,}",
            required: true
        },
        {
            id: 3,
            type: "text",
            name: "email",
            placeholder: "Email",
            label: "Email",
            errorMessage: "Incorrect email",
            pattern: "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            required: true
        },
        {
            id: 4,
            type: "text",
            name: "phoneNumber",
            placeholder: "Phone number",
            label: "Phone number",
            errorMessage: "Phone number should start from '8' and contain 11 digits",
            pattern: "8[0-9]{10}",
            required: true
        },
        {
            id: 5,
            type: "password",
            name: "password",
            placeholder: "Password",
            label: "Password",
            errorMessage: "Length should be 8 characters or more. Should contain at least one lower" +
                " and one capital character, one digit and one special symbol",
            pattern: "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$",
            required: true
        },
        {
            id: 6,
            type: "password",
            name: "confirmPassword",
            placeholder: "Confirm password",
            label: "Confirm password",
            errorMessage: "Passwords don't match!",
            pattern: values.password,
            required: true
        }
    ];

    const onChange = (e) => {
        setValues({...values, [e.target.name]:e.target.value})
    }

    const onSubmit = (e) => {
        e.preventDefault();
        //TODO
        // here is the code that should send a request to api
    }

    return (
        <div className={"RegistrationPage"}>
            <PageHeader />
            <br/>
            <form className={"RegistrationForm"} onSubmit={onSubmit}>
                <h2>Registration</h2>
                {inputProps.map((inputProp) => (
                    <FormInput
                        key={inputProp.id}
                        {...inputProp}
                        value={values[inputProp.name]}
                        onChange={onChange}
                    />
                ))}
                <button>Submit</button>
                <span>Already have an account? Login</span>
            </form>
        </div>
    );
};

export default RegistrationPage