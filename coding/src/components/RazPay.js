import '../css/RazorPay.css';
import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';


function RazPay() {
  const [amount, setAmount] = useState("");
  const [solve, setSolve] = useState(amount);


  const location = useLocation()
  console.log('order id', location.state.razorpayOrderId)
  // console.log('total payment', location.state.totalPay)

  const paymentId = location.state.razorpayOrderId
  console.log("payment id :", paymentId);

  const orderTotal = location.state.totalPay.orderTotal
  console.log("order total", orderTotal);

  const url = "https://jsonplaceholder.typicode.com/comments";

  async function fetchData() {
    const data = fetch(url)
      .then((res) => res.json())
      .then((getData) => setAmount(getData[0].id));
  }

  useEffect(() => {
    fetchData();
  }, [paymentId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (amount === "") {
      alert("please enter a valid amount");
    } else {
      var options = {
        key: "rzp_test_MfVx6zeC4NgkKw",
        currency: "INR",
        order_id: paymentId,
        image: "https://example.com/your_logo",
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
        <p>You are paying</p>
        <input type="number" value={orderTotal} onChange={(e) => setAmount(e.target.value)} readOnly />
      </div>
      <button className="btn" onClick={handleSubmit}>
        Click here to pay
      </button>
    </div>
  );
}

export default RazPay;




// ===========================================================================================
// import '../css/RazorPay.css';
// import React, { useEffect, useState } from 'react';
// import { useLocation } from 'react-router-dom';

// function RazPay(props) {
//   const [amount, setAmount] = useState("");
//   const [solve, setSolve] = useState(amount);

//   const location = useLocation()
//   // console.log('order id', location.state.razorpayOrderId)

//   const paymentId = location.state.razorpayOrderId
//   console.log("sfadf", paymentId);



//   const url = "https://jsonplaceholder.typicode.com/comments";

//   async function fetchData() {
//     const data = fetch(url)
//       .then((res) => res.json())
//       .then((getData) => setAmount(getData[0].id));
//   }


//   useEffect(() => {
//     fetchData();
//   }, [paymentId]);


//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     console.log("amount wala data", amount);

//     if (amount === "") {
//       alert("please enter a valid amount");
//     } else {
//       var options = {
//         key: "rzp_test_xN3UPfZc2ft7Q4",
//         // key_secret: "ij3TtklVsRUjTTXEYfnUFMJk",
//         // amount: amount * 100,
//         currency: "INR",
//         name: "HAARMK_PROJECTS",
//         order_id: paymentId,
//         prefill: {
//           contact: "0000000000",
//         },
//         notes: {
//           address: "Razorpay Corporate office",
//         },
//         theme: {
//           color: "#3399cc",
//         },
//       };

//       // try {
//       //   const response = await fetch("/your-server-side-script", {
//       //     method: "POST",
//       //     headers: {
//       //       "Content-Type": "application/json",
//       //     },
//       //     body: JSON.stringify({
//       //       amount: options.amount,
//       //       currency: options.currency,
//       //     }),
//       //   });

//       //   const data = await response.json();
//       //   options.handler = function (response) {
//       //     alert("Payment successful. Transaction ID: " + response.razorpay_payment_id);
//       //   };
//       // } catch (err) {
//       //   options.handler = function (response) {
//       //     alert("Payment failed. Transaction ID: " + response.razorpay_payment_id);
//       //   };
//       // }

//       var pay = new window.Razorpay(options);
//       pay.open();
//     }
//   };

//   return (
//     <div className="App">
//       <h2>HAARMK Payment Portal</h2>
//       <br />
//       <div className="input">
//         <p>You are paying</p>
//         <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} readOnly />
//       </div>
//       <button className="btn" onClick={handleSubmit}>
//         Click here to pay
//       </button>
//     </div>
//   );
// }

// export default RazPay;