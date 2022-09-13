import React from "react";
import { toast } from "react-toastify";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCopy, faCheck } from "@fortawesome/free-solid-svg-icons";
import "react-toastify/dist/ReactToastify.css";
export default function CodeCopyBtn({ children }) {
  const [copyOk, setCopyOk] = React.useState(false);
  const iconColor = copyOk ? "#ffffff" : "#ddd";
  const icon = copyOk ? faCheck : faCopy;

  const handleClick = (e) => {
    navigator.clipboard.writeText(children[0].props.children[0]);
    setCopyOk(true);
    notify();
    setTimeout(() => {
      setCopyOk(false);
    }, 500);
  };

  const notify = () =>
    toast.success("Copied!", {
      position: "top-center",
      autoClose: 500,
      hideProgressBar: true,
      // closeOnClick: true,
      // pauseOnHover: true,
      // draggable: true,
      // progress: 0,
    });

  return (
    <div className="code-copy-btn">
      <div>
        <FontAwesomeIcon
          icon={icon}
          onClick={handleClick}
          style={{ color: iconColor }}
          size="lx"
        />
      </div>
    </div>
  );
}
