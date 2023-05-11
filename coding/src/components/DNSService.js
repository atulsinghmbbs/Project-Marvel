import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { bakendBaseUrl, bakendHeader } from './BaseUrl';
import "./DNSService.css";


const DNSService = () => {
    let location = useLocation()
    //get data
    const [data, setData] = useState([]);
    const [serviceId, setServiceID] = useState(location.state.serviceId);
    const [editable, setEditable] = useState(false)

    // setServiceID()
    const onLoad = () => {
        fetch(bakendBaseUrl + '/services/domains/dns?serviceId=' + serviceId, {
            method: 'GET',
            headers: bakendHeader,
        }).then((response) => response.json())
            .then((json) => {
                setData(json.records)
                setEditable(false)
            })
            .catch((error) => {
                window.alert("Server Problem")
            })
    }
    useEffect(() => {
        onLoad();
    }, [])








    //open popup
    function handleClick() {
        setPopup(true)
    }
    //post the data
    const [host, setHost] = useState("")
    const [type, setType] = useState("")
    const [answer, setAnswer] = useState("")
    const [ttl, setTtl] = useState("")
    const [priority, setpriority] = useState("")
    const [showPopup, setPopup] = useState(false)
    const [newDns, setNewDns] = useState([])
    const [submitedBnt, setSubmitedBtn] = useState(false)

    function handleSubmit(e) {
        e.preventDefault();
        setSubmitedBtn(true)
        setPopup(false)

        fetch(bakendBaseUrl + '/services/domains/dns?serviceId=' + serviceId, {
            method: 'POST',
            body: JSON.stringify({
                host: host,
                type: type,
                answer: answer,
                ttl: ttl,
                priority: priority,

            }),
            headers: bakendHeader,
        })
            .then((response) => response.json())
            .then((json) => setNewDns(json))
            .catch((error) => {
                window.alert("Try after sometimes")
            })
    }









    //update the data
    // const [updateData, setUpdateData] = useState([])
    function handleClickUpdate(dnsData) {
        axios.put(bakendBaseUrl + '/services/domains/dns?serviceId=' + serviceId + "&recordId=" + dnsData.id,
            {
                host: dnsData.host,
                type: dnsData.type,
                answer: dnsData.answer,
                ttl: dnsData.ttl,
                priority: dnsData.priority,
            }, {
            headers: bakendHeader,
        })
            .then((response) => {
                // setUpdateData(response.data);
                window.location.reload()
                setEditable(false);
                window.alert("Data updated successfully.");
            })
            .catch((error) => {
                window.alert("Failed to update data. Please try again later.");
            });

    }






    //notworking
    const handleClickEdit = (dnsData) => {
        data.map((item) => {
            if (item.id === dnsData) {
                setEditable(true);
            }
        });
    }









    //for enable editing
    const handleInputChange = (event, index) => {
        const { name, value } = event.target;
        const list = [...data];
        list[index][name] = value;
        setData(list);
    };









    //for delete
    let handleClickDelete = (dnsData) => {
        axios.delete(bakendBaseUrl + '/services/domains/dns?serviceId=' + serviceId + "&recordId=" + dnsData.id,
            {
                headers: bakendHeader,
            }
        ).then((response) => {
            setEditable(false)
            window.alert("deleted sucessfully.......")
            window.location.reload()
        })
            .catch((error) => {
                console.log(error)
                window.alert("faild to delete.......")
            })
    }








    return (
        <div className="dns-service">
            <div className="your-dns">
                <h6 className='heading'>Your DNS.....</h6>
                {data && data.map((dnsData, index) => (
                    <div key={dnsData.id} className="your-dns-data">
                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`domainName-${dnsData.domainName}`}>Domain Name : </label>
                            <input type="text" id={`domainName-${dnsData.domainName}`} value={dnsData.domainName} name='domain' onChange={(e) => handleInputChange(e, index)} />
                        </div>


                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`answer-${dnsData.answer}`}>Answer : </label>
                            <input type="text" id={`answer-${dnsData.answer}`} value={dnsData.answer} name='answer' onChange={(e) => handleInputChange(e, index)} disabled={!editable} />
                        </div>

                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`host-${dnsData.host}`}>Host : </label>
                            <input type="text" id={`host-${dnsData.host}`} name='host' value={dnsData.host} onChange={(e) => handleInputChange(e, index)} disabled={!editable} />
                        </div>

                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`type-${dnsData.type}`}>Type : </label>
                            <input type="text" id={`type-${dnsData.type}`} name='type' value={dnsData.type} onChange={(e) => handleInputChange(e, index)} disabled={!editable} />
                        </div>

                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`ttl-${dnsData.ttl}`}>Ttl : </label>
                            <input type="text" id={`ttl-${dnsData.ttl}`} name='ttl' value={dnsData.ttl} onChange={(e) => handleInputChange(e, index)} disabled={!editable} />
                        </div>

                        <div className="dnsdata-domain-namm-same">
                            <label htmlFor={`fqdn-${dnsData.id}`}>Fqdl : </label>
                            <input type="text" id={`fqdn-${dnsData.id}`} name="fqdn" value={dnsData.fqdn} onChange={(e) => handleInputChange(e, index)} disabled={!editable}
                            />
                        </div>

                        <div className="dnsdata-domain-namm-same hide-field">
                            <label htmlFor={`id-${dnsData.id}`}>id : </label>
                            <input type="text" id={`id-${dnsData.id}`} name="id" value={dnsData.id} readOnly onChange={(e) => handleInputChange(e, index)} />
                        </div>

                        <div className='update-dns-delete-dns'>
                            <button className='update-dns' onClick={() => handleClickEdit(dnsData.id)}>Edit</button>
                            <button className='update-dns' onClick={(e) => handleClickUpdate(dnsData)}>Update</button>
                            <button className='delete-dns' onClick={(e) => handleClickDelete(dnsData)}>Delete</button>
                        </div>

                    </div>
                ))}
            </div>


            <div className="add-dns">
                <button className='add-dns-btn' onClick={handleClick}>Add DNS</button>
                {showPopup && (
                    <div className="dns-form">
                        <form onSubmit={handleSubmit}>
                            <div className='dns-forn-input'>
                                <label htmlFor="1">Host : </label>
                                <input type="text" value={host} id="1" required onChange={(e) => setHost(e.target.value)} />
                            </div>
                            <div className='dns-forn-input'>
                                <label htmlFor="2">Type : </label>
                                <select className='add-dns-option' value={type} onChange={(e) => setType(e.target.value)}>
                                    <option value="">Select Type</option>
                                    <option value="A">A</option>
                                    <option value="AAAA">AAAA</option>
                                    <option value="ANAME">ANAME</option>
                                    <option value="CNAME">CNAME</option>
                                    <option value="MX">MX</option>
                                    <option value="NX">NX</option>
                                    <option value="SRV">SRV</option>
                                    <option value="TXT">TXT</option>
                                </select>
                            </div>
                            <div className='dns-forn-input'>
                                <label htmlFor="3">Answer : </label>
                                <input type="text" value={answer} id="3" required onChange={(e) => setAnswer(e.target.value)} />
                                <i class="fa-sharp fa-solid fa-circle-exclamation"></i>
                            </div>
                            <div className='dns-forn-input'>
                                <label htmlFor="4">Ttl : </label>
                                <input type="text" value={ttl} id="4" required onChange={(e) => setTtl(e.target.value)} />
                                <img src="" alt="" />
                            </div>
                            <div className='dns-forn-input'>
                                <label htmlFor="5">Priority : </label>
                                <input type="text" id="5" value={priority} required onChange={(e) => setpriority(e.target.value)} />
                            </div>
                            <div>
                                <button type='submit'>Submit</button>
                            </div>
                        </form>
                    </div>
                )}
                {submitedBnt && <p>Thank you for submitting the form!</p>}
            </div>
            <div className="form-hover-item">
                <p>
                    Answer is either the IP address for A or AAAA records; the target for ANAME, CNAME, MX, or NS records; the text for TXT records. For SRV records, answer has the following format: "weight port target" e.g. "1 5061 sip.example.org".
                </p>
            </div>

        </div>
    )
}

export default DNSService
