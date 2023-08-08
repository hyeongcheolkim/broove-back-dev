// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "../contracts-protocol/utils/Context.sol";
import "../contracts-protocol/access/AccessControlEnumerable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Pausable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Enumerable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Mintable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Burnable.sol";
import "../contracts-protocol/utils/Counters.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17MetadataMintable.sol";

contract GrooveNft is KIP17MetadataMintable{
    using Counters for Counters.Counter;

    constructor(string memory name_, string memory symbol_, address root) KIP17(name_, symbol_){
        _setupRole(DEFAULT_ADMIN_ROLE, root);
    }

    Counters.Counter private _tokenIdTracker;

    function _beforeTokenTransfer(
        address from,
        address to,
        uint256 tokenId
    ) internal virtual override(KIP17) {
        super._beforeTokenTransfer(from, to, tokenId);
        _tokenIdTracker.increment();
    }

    function mintWithTokenURI(
        address to,
        string memory _tokenURI
    ) public onlyRole(MINTER_ROLE) returns (bool) {
        uint256 currentTokenId = _tokenIdTracker.current();
        _safeMint(to, currentTokenId);
        _setTokenURI(currentTokenId, _tokenURI);
        return true;
    }

    function currentTokenId() public view returns (uint256){
        return _tokenIdTracker.current();
    }
}