import Navbar from "../components/Navbar";
import ItemCard from "../components/ItemCard";
import axios from "axios";
import { useEffect, useState, useRef } from "react";
import { useSearchParams } from "react-router-dom";
import { toast } from "react-toastify";

const ItemsPage = () => {
  const [items, setItems] = useState([]),
    [files, setFiles] = useState([]),
    [searchParams] = useSearchParams(),
    code = searchParams.get("code"),
    messageRef = useRef(null),
    durationRef = useRef(null),
    [fileSelectedNum, setFileSelectedNum] = useState(0);
  //

  const handleFileChange = (e) => {
    const files = [...e.target.files];
    setFiles(files);
    setFileSelectedNum(files.length);
  };

  const createNewItem = (event, message, duration, files) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("code", code);
    formData.append("message", message);
    formData.append("duration", duration);
    files.forEach((file) => {
      formData.append("files", file);
    });
    axios
      .post(`${process.env.REACT_APP_API_URL}/item`, formData, {
        headers: {
          "Content-type": "multipart/form-data",
        },
      })
      .then(() => {
        getItems(code);
      })
      .catch((error) => {
        console.log(error);
        toast.error(error.response.data.message, {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
          theme: "dark",
        });
      });
  };

  const getItems = (code) => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/item?code=${code}`)
      .then((res) => {
        if (res.status === 200) {
          setItems(res.data.reverse());
        }
      })
      .catch((error) => {
        toast.error(error.response.data.message, {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: false,
          progress: undefined,
          theme: "dark",
        });
        setItems([]);
      });
  };

  useEffect(() => {
    getItems(code);
  }, [code]);

  return (
    <div>
      <Navbar />
      <div className="flex flex-col">
        <div className="flex flex-col lg:flex-row items-center lg:justify-around m-8">
          <p className=" text-primary text-5xl font-medium">{`access code: ${code}`}</p>
          <div className="rounded-3xl bg-background shadow-custom">
            <form
              onSubmit={(e) => {
                createNewItem(
                  e,
                  messageRef.current.value,
                  durationRef.current.value,
                  files
                );
              }}
            >
              <div className="flex flex-col lg:flex-row px-4">
                <input
                  type="text"
                  ref={messageRef}
                  placeholder="message..."
                  required
                  className="flex-1 lg:flex-auto text-xl font-medium outline-none text-secondary bg-transparent p-4 lg:p-0"
                />
                <input
                  type="number"
                  ref={durationRef}
                  minLength="1"
                  min="0"
                  placeholder="duration (min)"
                  required
                  className="flex-1 text-xl font-medium outline-none text-secondary bg-transparent p-4 lg:p-0"
                />
                <label
                  htmlFor="file"
                  className="p-4 items-center text-primary font-black text-base cursor-pointer"
                >
                  {fileSelectedNum > 0
                    ? `${fileSelectedNum} file selected`
                    : "add files"}
                </label>
                <input
                  id="file"
                  type="file"
                  multiple="multiple"
                  className="w-0 h-0"
                  onChange={handleFileChange}
                />
                <input
                  type="submit"
                  value="| create new item"
                  className="text-secondary font-black text-lg cursor-pointer p-4 lg:p-0"
                />
              </div>
            </form>
          </div>
        </div>
        <div className="flex flex-col justify-center items-center w-full mt-12">
          {items.length > 0 ? (
            items.map((item) => <ItemCard key={item.id} item={item} />)
          ) : (
            <h2 className="text-xl text-primary">
              There is no items connected to this code!
            </h2>
          )}
        </div>
      </div>
    </div>
  );
};

export default ItemsPage;
