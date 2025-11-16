// ========================================
// ECHOGUARD LANDING PAGE - INTERACTIVE ELEMENTS
// Vanilla JavaScript (<200 LOC)
// ========================================

// Mobile Menu Toggle
const mobileMenuToggle = document.querySelector('.mobile-menu-toggle');
const navLinks = document.querySelector('.nav-links');

if (mobileMenuToggle) {
    mobileMenuToggle.addEventListener('click', () => {
        const isExpanded = mobileMenuToggle.getAttribute('aria-expanded') === 'true';
        mobileMenuToggle.setAttribute('aria-expanded', !isExpanded);
        navLinks.classList.toggle('active');
    });
    
    // Close menu when clicking a link
    navLinks.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', () => {
            mobileMenuToggle.setAttribute('aria-expanded', 'false');
            navLinks.classList.remove('active');
        });
    });
}

// ========================================
// Privacy Score Gauge Animation
// ========================================

function animateGauge() {
    const gaugeProgress = document.getElementById('gaugeProgress');
    const gaugeScore = document.getElementById('gaugeScore');
    
    if (!gaugeProgress || !gaugeScore) return;
    
    let score = 0;
    const targetScore = 87;
    const duration = 2000; // 2 seconds
    const increment = targetScore / (duration / 16); // 60fps
    
    const animate = () => {
        score += increment;
        if (score >= targetScore) {
            score = targetScore;
        } else {
            requestAnimationFrame(animate);
        }
        
        gaugeScore.textContent = Math.round(score);
        
        // Calculate stroke-dashoffset (502.65 is full circle, 0-100 scale)
        const offset = 502.65 - (502.65 * score / 100);
        gaugeProgress.style.strokeDashoffset = offset;
    };
    
    // Start animation when element is in viewport
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                animate();
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.5 });
    
    const gaugeContainer = document.querySelector('.gauge-container');
    if (gaugeContainer) {
        observer.observe(gaugeContainer);
    }
}

// ========================================
// Timeline Scrubber
// ========================================

const timelineEvents = [
    { time: '9:41 AM', app: 'WhatsApp', permission: 'Microphone Access', icon: '🎤' },
    { time: '9:38 AM', app: 'Maps', permission: 'Location Access', icon: '📍' },
    { time: '9:35 AM', app: 'Instagram', permission: 'Camera Access', icon: '📷' },
    { time: '9:30 AM', app: 'Chrome', permission: 'Location Access', icon: '📍' },
    { time: '9:25 AM', app: 'Spotify', permission: 'Storage Access', icon: '💾' },
    { time: '9:20 AM', app: 'Gmail', permission: 'Contacts Access', icon: '👥' },
    { time: '9:15 AM', app: 'Facebook', permission: 'Camera Access', icon: '📷' },
    { time: '9:10 AM', app: 'WhatsApp', permission: 'Microphone Access', icon: '🎤' },
    { time: '9:05 AM', app: 'Weather', permission: 'Location Access', icon: '📍' },
    { time: '9:00 AM', app: 'Calendar', permission: 'Calendar Access', icon: '📅' }
];

function initTimelineScrubber() {
    const slider = document.getElementById('timelineSlider');
    const currentDisplay = document.getElementById('timelineCurrent');
    
    if (!slider || !currentDisplay) return;
    
    const updateTimeline = (value) => {
        // Map slider value (0-100) to events array index
        const index = Math.floor((value / 100) * (timelineEvents.length - 1));
        const event = timelineEvents[index];
        
        currentDisplay.innerHTML = `
            <span class="event-time">${event.time}</span>
            <span class="event-app">${event.icon} ${event.app}</span>
            <span class="event-permission">${event.permission}</span>
        `;
    };
    
    slider.addEventListener('input', (e) => {
        updateTimeline(e.target.value);
    });
    
    // Initialize with latest event
    updateTimeline(100);
    
    // Auto-play demo
    let autoPlayInterval;
    const startAutoPlay = () => {
        let value = 100;
        autoPlayInterval = setInterval(() => {
            value -= 2;
            if (value < 0) value = 100;
            slider.value = value;
            updateTimeline(value);
        }, 150);
    };
    
    const stopAutoPlay = () => {
        clearInterval(autoPlayInterval);
    };
    
    // Start auto-play when in viewport
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                startAutoPlay();
            } else {
                stopAutoPlay();
            }
        });
    }, { threshold: 0.3 });
    
    observer.observe(slider);
    
    // Pause on user interaction
    slider.addEventListener('mousedown', stopAutoPlay);
    slider.addEventListener('touchstart', stopAutoPlay);
}

// ========================================
// Smooth Scroll for Anchor Links
// ========================================

document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        const href = this.getAttribute('href');
        if (href === '#') return;
        
        e.preventDefault();
        const target = document.querySelector(href);
        
        if (target) {
            const navHeight = document.querySelector('.navbar').offsetHeight;
            const targetPosition = target.offsetTop - navHeight;
            
            window.scrollTo({
                top: targetPosition,
                behavior: 'smooth'
            });
        }
    });
});

// ========================================
// Navbar Scroll Effect
// ========================================

let lastScroll = 0;
const navbar = document.querySelector('.navbar');

window.addEventListener('scroll', () => {
    const currentScroll = window.pageYOffset;
    
    if (currentScroll > 100) {
        navbar.style.boxShadow = '0 4px 20px rgba(0, 0, 0, 0.3)';
    } else {
        navbar.style.boxShadow = 'none';
    }
    
    lastScroll = currentScroll;
});

// ========================================
// FAQ Toggle Analytics (optional tracking)
// ========================================

document.querySelectorAll('.faq-item').forEach(item => {
    item.addEventListener('toggle', (e) => {
        if (e.target.open) {
            const question = e.target.querySelector('summary').textContent;
            // Analytics event would go here
            console.log('FAQ opened:', question);
        }
    });
});

// ========================================
// Initialize All Interactive Elements
// ========================================

document.addEventListener('DOMContentLoaded', () => {
    animateGauge();
    initTimelineScrubber();
    
    // Lazy load images
    if ('IntersectionObserver' in window) {
        const imageObserver = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const img = entry.target;
                    if (img.dataset.src) {
                        img.src = img.dataset.src;
                        img.removeAttribute('data-src');
                        imageObserver.unobserve(img);
                    }
                }
            });
        });
        
        document.querySelectorAll('img[data-src]').forEach(img => {
            imageObserver.observe(img);
        });
    }
});

// ========================================
// Track CTA Button Clicks (for analytics)
// ========================================

document.querySelectorAll('.btn-primary, .cta-btn').forEach(button => {
    button.addEventListener('click', (e) => {
        const buttonText = e.currentTarget.textContent.trim();
        console.log('CTA clicked:', buttonText);
        // Send to analytics service here
    });
});
