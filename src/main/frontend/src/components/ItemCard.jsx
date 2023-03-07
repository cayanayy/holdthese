import axios from "axios";

const ItemCard = ({ item }) => {
  var leftTimeMilis = Date.parse(item.unableAt) - Date.now();
  var day = Math.floor(leftTimeMilis / 86400000);
  var hour = Math.floor((leftTimeMilis % 86400000) / 3600000);
  var minutes = Math.round(((leftTimeMilis % 86400000) % 3600000) / 60000);
  var untilUnable =
    day > 0
      ? `${day} days, ${hour} hours left`
      : hour > 0
      ? `${hour} hours, ${minutes} minutes left`
      : `${minutes} minutes left`;
  const dowloadFiles = () => {
    item.files.forEach((file, i) => {
      console.log(file);
      axios
        .get(
          `${process.env.REACT_APP_API_URL}/file?accessCode=${file.fileAccessCode}`
        )
        .then(async (res) => {
          const base64Response = await fetch(
            `data:${file.type};base64,${res.data}`
          );
          const newFile = await base64Response.blob();
          const fileName = file.name;
          const link = document.createElement("a");
          if (link.download !== undefined) {
            const url = URL.createObjectURL(newFile);
            link.setAttribute("href", url);
            link.setAttribute("download", fileName);
            link.style.visibility = "hidden";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
          }
        });
    });
  };

  return (
    <div
      className="cursor-pointer select-text w-4/5 m-1 shadow-custom py-4 px-7 rounded-full bg-transparent flex justify-between text-secondary"
      onClick={() => navigator.clipboard.writeText(item.message)}
    >
      <div>
        <p className="text-lg">{item.message}</p>
      </div>

      <div className="flex flex-col sm:flex-row">
        {[...item.files].length > 0 ? (
          <button className="mr-4 font-black" onClick={dowloadFiles}>
            download files
          </button>
        ) : (
          <></>
        )}
        <p className="flex items-center text-sm">{untilUnable}</p>
      </div>
    </div>
  );
};

export default ItemCard;