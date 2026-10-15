import React from 'react';

interface FooterProps {
  onSelectSection: (section: string) => void;
  onOpenSubscribe: () => void;
  onOpenLogin: () => void;
}

export const Footer: React.FC<FooterProps> = ({
  onSelectSection,
  onOpenSubscribe,
  onOpenLogin,
}) => {
  return (
    <footer className="w-full border-t-2 border-black max-w-[1340px] mx-auto px-4 pt-6 pb-12 text-[#333333]">
      <div className="flex items-center justify-between pb-4 border-b border-nyt">
        <div className="font-masthead text-2xl text-black">The New York Times</div>
        <div className="text-[11px] text-[#666]">
          <button
            onClick={onOpenSubscribe}
            className="hover:underline font-medium text-black cursor-pointer"
          >
            Subscribe
          </button>{' '}
          |{' '}
          <button
            onClick={onOpenSubscribe}
            className="hover:underline cursor-pointer"
          >
            Home Delivery
          </button>{' '}
          |{' '}
          <button
            onClick={onOpenSubscribe}
            className="hover:underline cursor-pointer"
          >
            Digital Subscriptions
          </button>
        </div>
      </div>

      {/* Category Footnotes Directory */}
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-6 gap-4 py-6 text-[11px] font-medium border-b border-nyt">
        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">News</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={() => onSelectSection('U.S.')} className="hover:underline cursor-pointer">
                Home Page
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('U.S.')} className="hover:underline cursor-pointer">
                U.S.
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('World')} className="hover:underline cursor-pointer">
                World
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('U.S.')} className="hover:underline cursor-pointer">
                Politics
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Business')} className="hover:underline cursor-pointer">
                Business
              </button>
            </li>
          </ul>
        </div>

        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">Opinion</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={() => onSelectSection('Opinion')} className="hover:underline cursor-pointer">
                Today's Opinion
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Opinion')} className="hover:underline cursor-pointer">
                Columnists
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Opinion')} className="hover:underline cursor-pointer">
                Editorials
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Opinion')} className="hover:underline cursor-pointer">
                Guest Essays
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Opinion')} className="hover:underline cursor-pointer">
                Letters
              </button>
            </li>
          </ul>
        </div>

        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">Arts</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={() => onSelectSection('Arts')} className="hover:underline cursor-pointer">
                Books
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Arts')} className="hover:underline cursor-pointer">
                Movies
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Arts')} className="hover:underline cursor-pointer">
                Music
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Arts')} className="hover:underline cursor-pointer">
                Television
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Arts')} className="hover:underline cursor-pointer">
                Theater
              </button>
            </li>
          </ul>
        </div>

        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">Living</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={() => onSelectSection('Lifestyle')} className="hover:underline cursor-pointer">
                Style
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Cooking')} className="hover:underline cursor-pointer">
                Food
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Lifestyle')} className="hover:underline cursor-pointer">
                Travel
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Lifestyle')} className="hover:underline cursor-pointer">
                Love
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Lifestyle')} className="hover:underline cursor-pointer">
                Real Estate
              </button>
            </li>
          </ul>
        </div>

        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">More</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={() => onSelectSection('Cooking')} className="hover:underline cursor-pointer">
                Cooking
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Wirecutter')} className="hover:underline cursor-pointer">
                Wirecutter
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('The Athletic')} className="hover:underline cursor-pointer">
                The Athletic
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('Games')} className="hover:underline cursor-pointer">
                Games
              </button>
            </li>
            <li>
              <button onClick={() => onSelectSection('U.S.')} className="hover:underline cursor-pointer">
                Headway
              </button>
            </li>
          </ul>
        </div>

        <div>
          <h6 className="font-bold text-black uppercase text-[10px] mb-2 tracking-wider">Account</h6>
          <ul className="space-y-1 text-gray-600">
            <li>
              <button onClick={onOpenLogin} className="hover:underline cursor-pointer">
                Manage My Account
              </button>
            </li>
            <li>
              <button onClick={onOpenSubscribe} className="hover:underline cursor-pointer">
                Gift Subscriptions
              </button>
            </li>
            <li>
              <button onClick={() => {}} className="hover:underline cursor-pointer">
                Mobile Apps
              </button>
            </li>
            <li>
              <button onClick={() => {}} className="hover:underline cursor-pointer">
                Help & FAQ
              </button>
            </li>
          </ul>
        </div>
      </div>

      {/* Copyright & Legal Links */}
      <div className="pt-5 text-center text-[10px] text-gray-500 space-y-1">
        <div className="flex flex-wrap justify-center gap-x-3 gap-y-1">
          <a className="hover:underline" href="#">
            © 2026 The New York Times Company
          </a>
          <a className="hover:underline" href="#">
            NYTCo
          </a>
          <a className="hover:underline" href="#">
            Contact Us
          </a>
          <a className="hover:underline" href="#">
            Accessibility
          </a>
          <a className="hover:underline" href="#">
            Work with us
          </a>
          <a className="hover:underline" href="#">
            Advertise
          </a>
          <a className="hover:underline" href="#">
            Privacy Policy
          </a>
          <a className="hover:underline" href="#">
            Terms of Service
          </a>
          <a className="hover:underline" href="#">
            Your Privacy Choices
          </a>
        </div>
      </div>
    </footer>
  );
};
