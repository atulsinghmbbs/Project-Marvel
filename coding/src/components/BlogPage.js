 import React from 'react'
import './BlogPage.css'

const BlogPage = () => {
    return (
        <div className='blog-wrapper'>
            <div className='blog-first-image-wrapper'>
                <img src="./images/blog-page-first-image.jpg" className='fist-blog-image' height="550px" width="1400px" alt="" />
            </div>

            <div className='badge-box'>
                <button type="button" class="btn btn-primary tech-btn ">Technology</button>
                <h1>The Impact of Technology on the the Workplace:How Technology is changing</h1>
                <br />
                <p>August 20,2022</p>
            </div>

            <div className='adds-block-parent'>
                <div className='adds-block'>
                    <p>Advertisement</p>
                </div>
            </div>

            {/* Latest post */}

            <div className='latest-post'>
                <p>Latest Post</p>
            </div>

            {/* latest photo and and blogs */}

            <div className='blog-post-wrapper'>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-1.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-2.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-3.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-4.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-5.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-6.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-7.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-8.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
                <div className='blog-image-and-title-wrapper'>
                    <img src="./images/blog-content-9.jpg" alt="" height="300" width="400" />
                    <h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus </h4>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button class="btn btn-primary btn-lg">View All Post</button>
            </div>


        </div>
    )
}

 export default BlogPage;

