import React, { useEffect, useState } from 'react'
import "./DisplayFeedback.css"

// const DisplayFeedback = () => {

//     const [message, setMessage] = useState([]);
//     const [id, setId] = useState([]);

//     const url = "https://jsonplaceholder.typicode.com/comments";

//     async function fetchData() {
//         const response = await fetch(url);
//         const data = await response.json();
//         const res = data.map(comment => comment.body); 
//         const resid=data.map(item=> item.id);
//         setId(resid);
//         setMessage(res);

//     }

//     useEffect(() => {
//         fetchData();
//     }, []);


//     console.log(message)


//     return (
//         <div className='display-feedback'>
//             <marquee direction="left">
//                 {id}:{message}
//             </marquee>
//         </div>
//     )
// }

// export default DisplayFeedback





// function DisplayFeedback() {
//     const [comments, setComments] = useState([]);

//     useEffect(() => {
//         fetch("https://jsonplaceholder.typicode.com/comments")
//             .then((response) => response.json())
//             .then((data) => {
//                 const filteredComments = data.map(({ id, body }) => ({ id, body }));
//                 setComments(filteredComments);
//             })
//             .catch((error) => console.error(error));
//     }, []);

//     return (
//         <div className="marquee">
//             <marquee direction="up" height="400" width="350">
//                 {comments.map(({ id, body }) => (
//                     <div key={id}>
//                         <p><b>{id}</b>   
//                             {body}
//                         </p>
//                     </div>
//                 ))}
//             </marquee>
//         </div>

//     );
// }

// export default DisplayFeedback;




// function DisplayFeedback() {
//     const [comments, setComments] = useState([]);

//     useEffect(() => {
//         fetch("http://192.168.1.50:8888/feedbacks/")
//             .then((response) => response.json())
//             .then((data) => {
//                 const filteredComments = data.map(({ username,  comment}) => ({ username,  comment}));
//                 setComments(filteredComments);
//             })
//             .catch((error) => console.error(error));
//     }, []);


//     return (
//         <div className="marquee">
//             <marquee direction="up" height="400" width="350">
//                 {comments.map(({ username,  comment}) => (
//                     <div key={username}>
//                         <p><b>{username}</b>   
//                             {comment}
//                         </p>
//                     </div>
//                 ))}
//             </marquee>
//         </div>

//     );
// }

// export default DisplayFeedback;
// function DisplayFeedback() {
//     const [comments, setComments] = useState([]);
//     const baseImageUrl="http://192.168.1.50:8888/static/images/"
//     useEffect(() => {
//         fetch("http://192.168.1.50:8888/feedbacks/")
//             .then(response => response.json())
//             .then(data => {
//                 setComments(data);
//                 console.log("jhhjg",comments)
//             })
//             .catch(error => console.error(error));
//     }, []);



//     return (
//         <div className="marquee">
//             <marquee direction="up" height="400" width="350">
//                  { Array.isArray(comments) &&  comments.map(comment => (
//                     <div key={comment.id} className='feedback-componants'>
//                         <img src={baseImageUrl+comment.user.image} alt="User" />
//                         <b>{comment.user.firstName}</b>
//                         {comment.comment}
//                     </div>
//                 ))}
//             </marquee>
//         </div>
//     );
// }

// export default DisplayFeedback;

function DisplayFeedback() {
    const [comments, setComments] = useState([]);
    const baseImageUrl = "http://192.168.1.50:8888/static/images/";
  
    useEffect(() => {
      try {
        fetch("http://192.168.1.50:8888/feedbacks/")
          .then((response) => response.json())
          .then((data) => {
            setComments(data);
            console.log("jhhjg", comments);
          })
        //   .catch((error) => console.error(error));
      } catch (error) {
        console.error(error);
        setComments([]);
      }
    }, []);
  
    return (
      <div className="marquee">
        <marquee direction="up" height="400" width="350">
          {Array.isArray(comments) && comments.length > 0 ? (
            comments.map((comment) => (
              <div key={comment.id} className="feedback-componants">
                <img src={baseImageUrl + comment.user.image} alt="User" />
                <b>{comment.user.firstName}</b>
                {comment.comment}
              </div>
            ))
          ) : (
            <div>No feedback</div>
          )}
        </marquee>
      </div>
    );
  }
  
  export default DisplayFeedback;

  