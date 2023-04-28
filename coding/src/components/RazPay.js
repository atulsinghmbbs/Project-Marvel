import '../css/RazorPay.css';

import React, { useEffect, useState } from 'react';

function RazPay() {
  const [amount, setAmount] = useState("");
  const [solve, setSolve] = useState(amount);

  const url = "https://jsonplaceholder.typicode.com/comments";

  async function fetchData() {
    const data = fetch(url)
      .then((res) => res.json())
      .then((getData) => setAmount(getData[0].id));
  }

  useEffect(() => {
    fetchData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (amount === "") {
      alert("please enter a valid amount");
    } else {
      var options = {
        key: "rzp_test_MfVx6zeC4NgkKw",
        currency: "INR",
        order_id: "order_LiPzVmSrium6W5",
        image : "https://example.com/your_logo",
        prefill: {
          contact: "",
        },
        notes: {
          address: "Razorpay Corporate office",
        },
        theme: {
          color: "#3399cc",
        },
      };

      // try {
      //   const response = await fetch("/your-server-side-script", {
      //     method: "POST",
      //     headers: {
      //       "Content-Type": "application/json",
      //     },
      //     body: JSON.stringify({
      //       amount: options.amount,
      //       currency: options.currency,
      //     }),
      //   });

      //   const data = await response.json();
      //   options.handler = function (response) {
      //     alert("Payment successful. Transaction ID: " + response.razorpay_payment_id);
      //   };
      // } catch (err) {
      //   options.handler = function (response) {
      //     alert("Payment failed. Transaction ID: " + response.razorpay_payment_id);
      //   };
      // }

      var pay = new window.Razorpay(options);
      pay.open();
    }
  };

  return (
    <div className="App">
      <h2>HAARMK Payment Portal</h2>
      <br />
      <div className="input">
        {/* <p>You are paying</p> */}
        {/* <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} readOnly /> */}
      </div>
      <button className="btn" onClick={handleSubmit}>
        Click here to pay
      </button>
    </div>
  );
}

export default RazPay;








// import '../css/RazorPay.css';


// import React, { useEffect, useState } from 'react';

// function RazPay() {

//   const [amount, setAmount] = useState("");
//   const [solve, setSolve] = useState(amount)

//   const url = "https://jsonplaceholder.typicode.com/comments"


//   async function fetchData() {
//     const data = fetch(url).then((res) => res.json())
//       .then((getData) => console.log(setAmount(getData[0].id)))
      
//   }

//   useEffect(() => {
//     fetchData()
//   }, [])


//   const handleSubmit = (e) => {
//     e.preventDefault();
//     console.log("amount wala data", amount)

//     if (amount === "") {
//       alert("please enter a valid amount");
//     } else {
//       var options = {
//         key: "rzp_test_yKt32uKI6iM9vt",
//         key_secret: "WdkV3mRiU6pFEy7AtPUNJ58i",
//         amount: amount * 100,
//         currency: "INR",
//         name: "HAARMK_PROJECTS",
//         description: "for testing purpose",
//         handler: function (response) {
//           alert(response.razorpay_payment_id);
//         },
//         prefill: {
//           name: "",
//           email: "Dummy0@gmail.com",
//           contact: "0000000000"
//         },
//         notes: {
//           address: "Razorpay Corporate office"
//         },
//         theme: {
//           color: "#3399cc"
//         }
//       };
      
//       var pay = new window.Razorpay(options);
//       pay.open();
      
//     }
//   }

//   return (
//     <div className="App">
//       <h2>HAARMK Payment Portal</h2>
//       <br />
//       <div className='input'>
//         <p>You are paying</p>
//         <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} readOnly />
//       </div>
//       <button className='btn' onClick={handleSubmit}>Click here to pay</button>
//     </div>
//   );
// }


// export default RazPay;


{/* <div className="App">
      <h2>HAARMK Payment Portal</h2>
      <br />
      <div className='input'>
        <p>You are paying</p>
        <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} readOnly />
      </div>
      <button className='btn' onClick={handleSubmit}>Click here to pay</button>
      <button>close</button>
      {props.children}
    </div> */}

