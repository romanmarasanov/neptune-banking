import "../styles/FormInput.css"
import "../styles/RegistrationPage.css"
import {useState} from "react";

const FormInput = (props) => {
    const [focused, setFocused] = useState(false);

    const handleFocus = (e) => {
      setFocused(e.target.value !== "");
    }

    return (
        <div className={"FormInput"}>
            <label>{props.label}</label>
            <br/>
            <input
                {...props}
                onChange={props.onChange}
                key={props.id}
                focused={focused.toString()}
                onInput={handleFocus}
            />
            <br/>
            <span>{props.errorMessage}</span>
        </div>
    );
}

export default FormInput;