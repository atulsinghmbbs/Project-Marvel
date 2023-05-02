import './RazPay.css';
import React, { useState } from 'react';
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';


function RazPay() {
  const [amount, setAmount] = useState("");

  const [payId, setPayId] = useState("")

  const location = useLocation()
  console.log('razorpay me id', location.state.razorPayAllData)

  useEffect(() => {
    setPayId(location.state.razorPayAllData.razorpayOrderId)
    setAmount(location.state.razorPayAllData.total)
  }, [location.state.razorPayAllData])

  console.log("payId", payId)
  console.log(amount)



  const handleSubmit = async (e) => {
    e.preventDefault();

    if (amount === "") {
      alert("please enter a valid amount");
    } else {
      var options = {
        key: "rzp_test_MfVx6zeC4NgkKw",
        currency: "INR",
        order_id: payId,
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
    <div className="App" style={{ marginTop: 100 }}>
      <h2>HAARMK Payment Portal</h2>
      <br />
      <div className="input">
        <p>You are paying</p>
        <input type="number" value={amount} readOnly />
      </div>
      <button className="btn" onClick={handleSubmit}>
        Click here to pay
      </button>
    </div>
  );
}

export default RazPay;


